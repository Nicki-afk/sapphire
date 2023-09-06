package gyber.websocket.security.authenticate.filters;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import gyber.websocket.controllers.exceptions.ErrorRestResponse;
import gyber.websocket.controllers.exceptions.TokenLocalStorageException;
import gyber.websocket.models.User;
import gyber.websocket.security.authenticate.tokenManagement.TokenLocalStorageManager;
import gyber.websocket.security.authenticate.tokenManagement.TokenPairObject;


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

        /*
         * @nic_ko : Дописать  
         */
            try {
                if(!tokenLocalStorageManager.exisistRefresh(refreshHeader)){
                    constructErrorResponse(response, "Refresh token does not exist, please login to get tokens");
                    return;

                }else if(tokenLocalStorageManager.exisistRefresh(refreshHeader) && !tokenLocalStorageManager.refreshTokenIsValid(refreshHeader)){
                    constructErrorResponse(response, "The refresh token has expired, please re-authorize");
                    return;
                    
                }


                constructTheOkResponse(response, refreshHeader);


            } catch (TokenLocalStorageException e) {
               constructErrorResponse(response, "An error occurred while processing the token", e);
               return;
            } catch (Exception e){
                constructErrorResponse(response, "A critical error occurred while processing the token by the filter", e);
                return;
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
          
            e.printStackTrace();
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
