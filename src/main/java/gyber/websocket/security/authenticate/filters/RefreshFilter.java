package gyber.websocket.security.authenticate.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import gyber.websocket.controllers.exceptions.ErrorRestResponse;
import gyber.websocket.controllers.exceptions.TokenLocalStorageException;
import gyber.websocket.models.User;
import gyber.websocket.security.authenticate.tokenManagement.TokenLocalStorageManager;
import gyber.websocket.security.authenticate.tokenManagement.TokenPairObject;

public class RefreshFilter extends OncePerRequestFilter{



    @Autowired
    private TokenLocalStorageManager tokenLocalStorageManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String refreshHeader = request.getHeader("Refresh");

        if(refreshHeader == null || refreshHeader.isEmpty()){
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
            } catch (TokenLocalStorageException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            response.setStatus(200);
            return;
            

        }



    }
    

    public void constructErrorResponse(HttpServletResponse response , String message ){
        ErrorRestResponse errorRestResponse = new ErrorRestResponse(message , 401);
        

        response.setStatus(401);
        response.setContentType("application/json");
        
        try {
            
            response.getWriter().write(new ObjectMapper().writeValueAsString(errorRestResponse));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return;
    }

      public void constructErrorResponse(HttpServletResponse response , String message , TokenPairObject tokenPairObject ){
        ErrorRestResponse errorRestResponse = new ErrorRestResponse(message , 401);
        errorRestResponse.addErrorDataLink("old_token_pair" , tokenPairObject);

        

        response.setStatus(401);
        response.setContentType("application/json");
        
        try {
            
            response.getWriter().write(new ObjectMapper().writeValueAsString(errorRestResponse));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return;
    }

}
