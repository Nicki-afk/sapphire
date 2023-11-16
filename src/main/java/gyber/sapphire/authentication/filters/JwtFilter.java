package gyber.sapphire.authentication.filters;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import gyber.sapphire.authentication.tokens.JwtService;
import gyber.sapphire.authentication.tokens.TokenLocalStorageManager;
import gyber.sapphire.errors.TokenLocalStorageException;
import gyber.sapphire.profile.UserCustomDetails;
import gyber.sapphire.profile.UserCustomDetailsService;

@Service
public class JwtFilter extends CustomAbstractPerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserCustomDetailsService userIPFSCustomDetailsService;

    @Autowired private TokenLocalStorageManager tokenManager;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       

        if(thisURLCanBeUsedWithoutAFilter((request.getRequestURI()))){
            filterChain.doFilter(request, response);
            return;
        }

        /*
         * @nic_ko : Вынести в отдельный метод 
         */

       String headerData =  request.getHeader("Authorization");
       String token = "";

    
        if(headerData != null && headerData.startsWith("Bearer ")){
            token = headerData.substring(7);
            try {
              if(this.tokenManager.getUserByJwt(token) == null){

                constructErrorResponse(response, "An exception error occurred while processing the jwt token, your token was not found" , new TokenLocalStorageException( "This user's token was not found"));
                return;
             }



             if(!this.tokenManager.jwtTokenIsValid(token)){
                constructErrorResponse(response, "Your jwt token is expired, refresh token pair for further work" , new TokenLocalStorageException("User token has expired"));
                return;
             }

        
                UserCustomDetails userCustomDetails = new UserCustomDetails(this.tokenManager.getUserByJwt(token));
                UsernamePasswordAuthenticationToken authData = new UsernamePasswordAuthenticationToken(userCustomDetails, null, userCustomDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authData);

                filterChain.doFilter(request, response);
                return;

            } catch (TokenLocalStorageException e) {
                constructErrorResponse(response, "Exception error occurred while processing jwt token , see post for more details", e);
            }catch(Exception e){
                constructErrorResponse(response, "A critical error occurred while processing the token by the filter", e);
            }
            

          

        }else{

            constructErrorResponse(response, "An exception error occurred while processing the jwt token, your token was not found or is null", new NullPointerException("Token JWT is NULL"));
        }

    }


    
}
