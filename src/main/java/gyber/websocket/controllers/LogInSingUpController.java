package gyber.websocket.controllers;

import java.util.Map;

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


    @PostMapping("/reg/simple_reg")
    public ResponseEntity registerUser(@RequestBody User user){

        return ResponseEntity.ok().build();
    }

    @PostMapping("/reg/metamask_reg")
    public ResponseEntity registerUserByMetamaskAddress(){
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login/nickname_passwd")
    public ResponseEntity logInUser(@RequestBody Map<String , String> userNameAndPasswd){
        return ResponseEntity.ok().build();

    }


    @PostMapping("/login/metamask_address")
    public ResponseEntity logInUserByMetamskAddress(@RequestBody Map<String , String>keyValue){

        return ResponseEntity.ok().build();
    }
}
