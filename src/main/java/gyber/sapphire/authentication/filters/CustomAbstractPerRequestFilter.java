package gyber.sapphire.authentication.filters;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import gyber.sapphire.errors.BetaTestKeyException;
import gyber.sapphire.errors.ErrorRestResponse;
import gyber.sapphire.errors.TokenLocalStorageException;

public abstract class CustomAbstractPerRequestFilter extends OncePerRequestFilter {




    public boolean thisURLCanBeUsedWithoutAFilter(String url){

        return url.startsWith("/post") || url.startsWith("/auth") || url.startsWith("/pub") || url.startsWith("/system");
    }



    public void constructErrorResponse(HttpServletResponse response , String message , Exception e ){

        ObjectMapper jsonConverter = new ObjectMapper();
        jsonConverter.registerModule(new JavaTimeModule());


        int statusResponse = e instanceof TokenLocalStorageException || e instanceof BetaTestKeyException ? 401 : 500;

        ErrorRestResponse errorRestResponse = new ErrorRestResponse(message , statusResponse);

        if(statusResponse == 500){

            errorRestResponse
            .addErrorDataLink("short_stack_trace" , Arrays.copyOf(e.getStackTrace(), 3))
            .addErrorDataLink("local_message", e.getLocalizedMessage());
            
        }
        

        response.setStatus(statusResponse);
        response.setContentType("application/json");
        
        try {
            
            response.getWriter().write(jsonConverter.writeValueAsString(errorRestResponse));
        } catch (IOException ioException) {
             ioException.printStackTrace();
        
        }

        return;
    }



    
}
