package gyber.websocket.security.authenticate.filters;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gyber.websocket.exceptions.BetaTestKeyException;
import gyber.websocket.exceptions.ErrorRestResponse;
import gyber.websocket.exceptions.TokenLocalStorageException;
import gyber.websocket.models.User;
import gyber.websocket.security.authenticate.tokenManagement.TokenPairObject;
import gyber.websocket.security.beta.BetaTestKeyManager;


@Service
public class KeyFilter extends OncePerRequestFilter{


    @Autowired
    private BetaTestKeyManager keyManager;

    @Autowired 
    private ObjectMapper objMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                
       

           String headerKeyParam = request.getHeader("Beta key");

            if(headerKeyParam == null || headerKeyParam.isEmpty()){
                constructErrorResponse(
                    response,
                    "Your request does not contain a beta test key. The project is in the early stages of testing and a test key is required to access the server", 
                    new BetaTestKeyException("Beta test key not found")
                );
                return;

            }


            /*
             * В сегменте кода ниже , выполняется условие проверки ключа 
             * если ключ по каким то причинам не валиден , он выкинет BetaTestKeyException / OtherException в 
             * котором будет объяснено почему ключ не прошел проверку. 
             * В случае если ключ валиден , он вернет true и фильтр пропустит запрос 
             * дальше по цепочке  
            */

            try{
                this.keyManager.isBetaKeyIsValid(headerKeyParam);

                filterChain.doFilter(request, response);
                return;

            }catch(Exception e){
                constructErrorResponse(response, "An error occurred while processing the key", e);
                return;
            }
    }


    public void constructErrorResponse(HttpServletResponse response , String message , Exception e ){


        int statusResponse = e instanceof BetaTestKeyException ? 401 : 500;
        ErrorRestResponse errorRestResponse = new ErrorRestResponse(message , statusResponse);
        errorRestResponse
        .addErrorDataLink("short_stack_trace" , Arrays.copyOf(e.getStackTrace(), 2))
        .addErrorDataLink("local_message", e.getLocalizedMessage());

        

        response.setStatus(statusResponse);
        response.setContentType("application/json");
        
        try {
            
            response.getWriter().write(objMapper.writeValueAsString(errorRestResponse));
        } catch (IOException ioException) {
            // TODO Auto-generated catch block
             ioException.printStackTrace();
        
        }

        return;
    }

    // public void constructTheOkResponse(HttpServletResponse response , String betaKey) throws TokenLocalStorageException, JsonProcessingException, IOException{

    
    //     // User user = this.tokenLocalStorageManager.getUserByRefresh(refreshHeader);
    //     // this.tokenLocalStorageManager.updateTokenPairUser(user);
    //     // TokenPairObject tokenPairObject = this.tokenLocalStorageManager.getTokenPairInUser(user);
    //     String jsonResponse = this.objMapper.writeValueAsString(betaKey);


    //     // response.setStatus(200);
    //     // response.setContentType("application/json");
    //     // response.getWriter().write(objectMapper.writeValueAsString(tokenPairObject));
    //     return;

    // }
    
}
