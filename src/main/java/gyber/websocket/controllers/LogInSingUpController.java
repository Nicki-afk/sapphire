package gyber.websocket.controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gyber.websocket.models.User;
import gyber.websocket.models.UserCustomDetails;
import gyber.websocket.models.repo.UserRepository;
import gyber.websocket.security.authenticate.tokenManagement.JwtService;
import gyber.websocket.security.authenticate.tokenManagement.RTService;
import gyber.websocket.security.authenticate.tokenManagement.TokenLocalStorageManager;
import gyber.websocket.security.authenticate.tokenManagement.TokenPairObject;

@Controller
@RequestMapping("/register")
public class LogInSingUpController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenLocalStorageManager tokenLocalStorageManager;
    


    @PostMapping
    public ResponseEntity postUser(@RequestBody User user){

        this.userRepository.save(user);
        
        return ResponseEntity.ok(authenticate(user)); 


    }


    @GetMapping("/get")
    public ResponseEntity getUser(@RequestParam("username") String username){

        User userIPFSModel = this.userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return ResponseEntity.ok(userIPFSModel);

    }



    public TokenPairObject authenticate(User user){
        UserCustomDetails userCustomDetails = new UserCustomDetails(user);
        UsernamePasswordAuthenticationToken userPrincipal = new UsernamePasswordAuthenticationToken(userCustomDetails.getUsername(), null, userCustomDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(userPrincipal);

        return this.tokenLocalStorageManager.addTokenPairForUser(user);
    }
}
