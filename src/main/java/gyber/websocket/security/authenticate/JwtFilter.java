package gyber.websocket.security.authenticate;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import gyber.websocket.models.UserIPFSCustomDetailsService;

@Service
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserIPFSCustomDetailsService userIPFSCustomDetailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       
       String headerData =  request.getHeader("Authorization");
       String token = "";

        if(headerData != null && headerData.startsWith("Bearer ")){
            token = headerData.substring(7);

            if(!jwtService.validateToken(token)){

                response.setStatus(401);
                return;

            }

            // Забераем имя пользователя с JWT
            String username = this.jwtService.getUserNameInToken(token);
            UserDetails userDetails = this.userIPFSCustomDetailsService.loadUserByUsername(username); // Загружаем детали пользователя 

            // Создаем объект аутентификации пользователя передаем в конструктор ( детали пользователя , пароль == null , права пользователя)
            // Данный конструктор используется для аутентифицированного пользователя 
            UsernamePasswordAuthenticationToken authData = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 

            // Сохраняем данные об аутентифицированном пользователе 
            SecurityContextHolder.getContext().setAuthentication(authData);

        }

    }
    
}
