package gyber.websocket.security.authenticate.filters;

import java.io.IOException;
import java.time.LocalDateTime;
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

import gyber.websocket.models.ErrorResponse;
import gyber.websocket.models.UserCustomDetailsService;
import gyber.websocket.security.authenticate.tokenManagement.JwtService;
import io.jsonwebtoken.ExpiredJwtException;

@Service
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserCustomDetailsService userIPFSCustomDetailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       
        if(request.getRequestURI().equals("/register") | request.getRequestURI().equals("/auth")){
            filterChain.doFilter(request, response);
            return;

        }

       String headerData =  request.getHeader("Authorization");
       String token = "";

        if(headerData != null && headerData.startsWith("Bearer ")){
            token = headerData.substring(7);

            if(!jwtService.validateToken(token)){

                String errorResponse = new ObjectMapper().writeValueAsString(new ErrorResponse(LocalDateTime.now() , 401 , new ExpiredJwtException(null, null, token) ,    "The user token has disappeared. You need to refresh the token for renewal or re-authorization."));
                response.setStatus(401);
                response.setContentType("application/json");
                response.getWriter().write(errorResponse);
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

            filterChain.doFilter(request, response);

        }else{

            // TODO : Вынести в отдельный метод 
            ObjectMapper objectMapper = new ObjectMapper();
            String errorMessage = objectMapper.writeValueAsString(
                Map.of("time" , new Date() , "code" , 401 , "message" , "User tokens JWT/RT Not found. Try to contact the address /auth for authorization")
            );

            response.setStatus(401);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(errorMessage);


            
            return;
        }

    }
    
}
