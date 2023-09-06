package gyber.websocket.security.authenticate.filters;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import gyber.websocket.controllers.exceptions.ErrorRestResponse;
import gyber.websocket.controllers.exceptions.TokenLocalStorageException;
import gyber.websocket.models.ErrorResponse;
import gyber.websocket.models.UserCustomDetails;
import gyber.websocket.models.UserCustomDetailsService;
import gyber.websocket.security.authenticate.tokenManagement.JwtService;
import gyber.websocket.security.authenticate.tokenManagement.TokenLocalStorageManager;
import io.jsonwebtoken.ExpiredJwtException;

@Service
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserCustomDetailsService userIPFSCustomDetailsService;

    @Autowired private TokenLocalStorageManager tokenManager;
    @Autowired private ObjectMapper objectMapper;
    



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       
        /*
         * @nic_ko : Вынести в отдельный метод 
         */
        // if(request.getRequestURI().equals("/register") | request.getRequestURI().equals("/auth")){
        //     filterChain.doFilter(request, response);
        //     return;

        // }

        if(skipTheRequestToThisAddressIfItIsInTheExceptions(request.getRequestURI())){
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

                constructErrorResponse(response, "An exception error occurred while processing the jwt token, your token was not found");
                return;
             }



             if(!this.tokenManager.jwtTokenIsValid(token)){
                constructErrorResponse(response, "Your jwt token is expired, refresh token pair for further work");
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


    public boolean skipTheRequestToThisAddressIfItIsInTheExceptions(String uri ) {


        return uri.equals("/register") || uri.equals("/auth");


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
             ioException.printStackTrace();
        
        }

        return;
    }
    
}
