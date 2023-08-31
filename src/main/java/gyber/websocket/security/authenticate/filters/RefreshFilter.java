package gyber.websocket.security.authenticate.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import gyber.websocket.security.authenticate.tokenManagement.TokenLocalStorageManager;

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
            if(!tokenLocalStorageManager.refreshTokenIsValid(refreshHeader)){
                response.setStatus(401);
                return;
            }

            response.setStatus(200);
            return;
            

        }



    }
    
}
