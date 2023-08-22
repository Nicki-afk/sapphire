package gyber.websocket.controllers;

import java.time.LocalDateTime;
import java.util.Map;

// import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gyber.websocket.models.User;
import gyber.websocket.models.UserIPFSModel;

@Controller()
@RequestMapping("/register")
public class LogInSingUpController {


    // @PostMapping
    // public ResponseEntity postUserEntity(@ResponseBody Map<String , String> clientData){
    //     String crypto = clientData.get("sign");




    // }


    @PostMapping
    public ResponseEntity postUser(@RequestBody UserIPFSModel userIPFSModel){



        return userIPFSModel == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok().build(); 


    }
}
