package gyber.websocket.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gyber.websocket.models.User;

@Controller("/user")
public class LogInSingUpController {


    @PostMapping("/reg")
    public ResponseEntity registerUser(@RequestBody User user){

        return ResponseEntity.ok().build();
    }
    
}
