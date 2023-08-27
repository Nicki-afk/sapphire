package gyber.websocket.controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import gyber.websocket.models.User;
import gyber.websocket.models.repo.UserRepository;

@Controller
@RequestMapping("/register")
public class LogInSingUpController {


    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public ResponseEntity postUser(@RequestBody User userIPFSModel){

        this.userRepository.save(userIPFSModel);


        return userIPFSModel == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok().build(); 


    }


    @GetMapping("/get")
    public ResponseEntity getUser(@RequestParam("username") String username){

        User userIPFSModel = this.userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return ResponseEntity.ok(userIPFSModel);

    }
}
