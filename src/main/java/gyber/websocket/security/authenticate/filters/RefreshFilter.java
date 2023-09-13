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

import gyber.websocket.exceptions.ErrorRestResponse;
import gyber.websocket.exceptions.TokenLocalStorageException;
import gyber.websocket.models.User;
import gyber.websocket.security.authenticate.tokenManagement.TokenLocalStorageManager;
import gyber.websocket.security.authenticate.tokenManagement.TokenPairObject;



public class RefreshFilter extends CustomAbstractPerRequestFilter{



    @Autowired private TokenLocalStorageManager tokenLocalStorageManager;
    @Autowired private ObjectMapper objectMapper;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        if(thisURLCanBeUsedWithoutAFilter((request.getRequestURI()))){
            filterChain.doFilter(request, response);
            return;

        }        
        
        String refreshHeader = request.getHeader("Refresh");

        if(refreshHeader == null || refreshHeader.isEmpty()){
            filterChain.doFilter(request, response); // Передаем на JwtFilter   
            return;

        }else{


            try {
                if(!tokenLocalStorageManager.exisistRefresh(refreshHeader)){
                    constructErrorResponse(response, "Refresh token does not exist, please login to get tokens" , new TokenLocalStorageException("Token not found"));
                    return;
                    

                }else if(tokenLocalStorageManager.exisistRefresh(refreshHeader) && !tokenLocalStorageManager.refreshTokenIsValid(refreshHeader)){
                    constructErrorResponse(response, "The refresh token has expired, please re-authorize" , new TokenLocalStorageException("Token is expired"));
                    return;
                    
                }


                constructTheOkResponse(response, refreshHeader);
                return;


            } catch (TokenLocalStorageException e) {
               constructErrorResponse(response, "An error occurred while processing the token", e);
            
            } catch (Exception e){
                constructErrorResponse(response, "A critical error occurred while processing the token by the filter", e);
            }

    

        }



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
