package gyber.sapphire.authentication.filters;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gyber.sapphire.authentication.tokens.TokenLocalStorageManager;
import gyber.sapphire.authentication.tokens.TokenPairObject;
import gyber.sapphire.errors.TokenLocalStorageException;
import gyber.sapphire.profile.User;



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
