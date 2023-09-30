package gyber.sapphire.controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gyber.sapphire.entities.User;
import gyber.sapphire.entities.UserCustomDetails;
import gyber.sapphire.entities.repositories.UserRepository;
import gyber.sapphire.exceptions.TokenLocalStorageException;
import gyber.sapphire.security.authenticate.tokenManagement.JwtService;
import gyber.sapphire.security.authenticate.tokenManagement.RTService;
import gyber.sapphire.security.authenticate.tokenManagement.TokenLocalStorageManager;
import gyber.sapphire.security.authenticate.tokenManagement.TokenPairObject;


@Controller
@RequestMapping("/post")
public class PostController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenLocalStorageManager tokenLocalStorageManager;
    


    @PostMapping("/save")
    public ResponseEntity postUser(@RequestBody User user) throws TokenLocalStorageException{

        this.userRepository.save(user);
        
        return ResponseEntity.ok(authenticate(user)); 


    }




    public TokenPairObject authenticate(User user) throws TokenLocalStorageException{
        UserCustomDetails userCustomDetails = new UserCustomDetails(user);
        UsernamePasswordAuthenticationToken userPrincipal = new UsernamePasswordAuthenticationToken(userCustomDetails.getUsername(), null, userCustomDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(userPrincipal);

        return this.tokenLocalStorageManager.addTokenPairForUser(user);
    }
}

