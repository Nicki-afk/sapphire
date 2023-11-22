package gyber.sapphire.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginProccessController {


    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping("/proccess/login")
    public ResponseEntity proccessLogin(
        @RequestParam("username") String username , 
        @RequestParam("password") String password
    ){

        System.out.println("Attempting to authenticate user: " + username);
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);

    

        Authentication isAuthinticate ;

        try{

         isAuthinticate = authenticationManager.authenticate(auth);

        }catch(Exception badCredentialsException){
            System.out.println("\n\nПользователь не может быть аутентифицирован\n\n");

            return ResponseEntity.badRequest().build();
        }

        System.out.println("\n\nПользователь успешно аутентифицирован\n\n");

        SecurityContextHolder.getContext().setAuthentication(auth);

        return ResponseEntity.ok().build();
    }
    
}
