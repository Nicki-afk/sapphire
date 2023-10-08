package gyber.sapphire.rest;

import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

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

import gyber.sapphire.authentication.tokens.JwtService;
import gyber.sapphire.authentication.tokens.RTService;
import gyber.sapphire.authentication.tokens.TokenLocalStorageManager;
import gyber.sapphire.authentication.tokens.TokenPairObject;
import gyber.sapphire.database.repositories.UserRepository;
import gyber.sapphire.errors.TokenLocalStorageException;
import gyber.sapphire.messaging.Chat;
import gyber.sapphire.profile.User;
import gyber.sapphire.profile.UserCustomDetails;


@Controller
@RequestMapping("/post")
public class PostController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenLocalStorageManager tokenLocalStorageManager;
    


    @PostMapping("/save")
    public ResponseEntity postUser(@RequestBody @Valid User user) throws TokenLocalStorageException{

        this.userRepository.save(user);
        
        return ResponseEntity.ok(authenticate(user)); 


    }

    @PostMapping("/chatnew")
    public ResponseEntity postNewUserChat(@RequestBody  @Valid Chat chat){
    
        // ... 

        return ResponseEntity.ok().build();
    }




    public TokenPairObject authenticate(User user) throws TokenLocalStorageException{
        UserCustomDetails userCustomDetails = new UserCustomDetails(user);
        UsernamePasswordAuthenticationToken userPrincipal = new UsernamePasswordAuthenticationToken(userCustomDetails.getUsername(), null, userCustomDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(userPrincipal);

        return this.tokenLocalStorageManager.addTokenPairForUser(user);
    }
}

