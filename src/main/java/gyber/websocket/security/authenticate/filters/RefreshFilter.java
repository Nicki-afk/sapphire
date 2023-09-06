package gyber.websocket.security.authenticate.filters;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gyber.websocket.controllers.exceptions.ErrorRestResponse;
import gyber.websocket.controllers.exceptions.TokenLocalStorageException;
import gyber.websocket.models.User;
import gyber.websocket.security.authenticate.tokenManagement.TokenLocalStorageManager;
import gyber.websocket.security.authenticate.tokenManagement.TokenPairObject;


/*
 * @nic_ko : Подумать над идей общего интерфейса в котором будут реализованны 
 *           методы для построения ошибок , и их ответов. Таким образом мы разгрузим 
 *           и сократим фильтр удалив лишнюю логику за которую фильтр не должен отвечать
 */

public class RefreshFilter extends OncePerRequestFilter{



    @Autowired private TokenLocalStorageManager tokenLocalStorageManager;
    @Autowired private ObjectMapper objectMapper;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String refreshHeader = request.getHeader("Refresh");

        if(refreshHeader == null ){
            filterChain.doFilter(request, response); // Передаем на JwtFilter   

        }else{


            try {
                if(!tokenLocalStorageManager.exisistRefresh(refreshHeader)){
                    constructErrorResponse(response, "Refresh token does not exist, please login to get tokens");
                    

                }else if(tokenLocalStorageManager.exisistRefresh(refreshHeader) && !tokenLocalStorageManager.refreshTokenIsValid(refreshHeader)){
                    constructErrorResponse(response, "The refresh token has expired, please re-authorize");
                    
                    
                }


                constructTheOkResponse(response, refreshHeader);


            } catch (TokenLocalStorageException e) {
               constructErrorResponse(response, "An error occurred while processing the token", e);
            
            } catch (Exception e){
                constructErrorResponse(response, "A critical error occurred while processing the token by the filter", e);
            }

    

        }



    }
    

    public void constructErrorResponse(HttpServletResponse response , String message ){
        ErrorRestResponse errorRestResponse = new ErrorRestResponse(message , 401);
        

        response.setStatus(401);
        response.setContentType("application/json");
        
        try {
            
            response.getWriter().write(objectMapper.writeValueAsString(errorRestResponse));
        } catch (IOException e) {
          
          constructErrorResponse(response, "An exception was thrown while processing and building the error response. Unable to submit error data. See the exception for detailed localization of the exception", e);
        }

        return;
    }

    public void constructErrorResponse(HttpServletResponse response , String message , Exception e ){


        int statusResponse = e instanceof TokenLocalStorageException ? 401 : 500;
        ErrorRestResponse errorRestResponse = new ErrorRestResponse(message , statusResponse);
        errorRestResponse
        .addErrorDataLink("short_stack_trace" , Arrays.copyOf(e.getStackTrace(), 2))
        .addErrorDataLink("local_message", e.getLocalizedMessage());

        

        response.setStatus(statusResponse);
        response.setContentType("application/json");
        
        try {
            
            response.getWriter().write(objectMapper.writeValueAsString(errorRestResponse));
        } catch (IOException ioException) {
            // TODO Auto-generated catch block
             ioException.printStackTrace();
        
        }

        return;
    }

    public void constructTheOkResponse(HttpServletResponse response , String refreshHeader) throws TokenLocalStorageException, JsonProcessingException, IOException{

    
        User user = this.tokenLocalStorageManager.getUserByRefresh(refreshHeader);
        this.tokenLocalStorageManager.updateTokenPairUser(user);
        TokenPairObject tokenPairObject = this.tokenLocalStorageManager.getTokenPairInUser(user);


        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(tokenPairObject));
        return;

    }

}
