package gyber.websocket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gyber.websocket.models.User;
import gyber.websocket.models.repo.UserRepository;

@Controller
@RequestMapping("/get")
public class GetDataController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/us")
    public ResponseEntity getUser(@RequestParam("username") String username){

        User userIPFSModel = this.userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return ResponseEntity.ok(userIPFSModel);

    }

    @GetMapping("/id")
    public ResponseEntity getUserById(@RequestParam("id") Long id){
        // ... 
        return ResponseEntity.ok().build();

    }

    @GetMapping("/crypto")
    public ResponseEntity getUserByCrypto(@RequestParam("wallet") String wallet){

        // ...
        
        return ResponseEntity.ok().build();

    }

    @GetMapping("/stat")
    public ResponseEntity getStatusById(@RequestParam("id") Long id){
        /// ...
        return ResponseEntity.ok().build();
    }

    /*
     * Загружвет следующие 10 чатов если пользователь свайпает
     * окно чатов вниз 
     */
    @GetMapping("/ochat")
    public ResponseEntity getOtherChats(@RequestParam("id") Long id){
        // ... 

        return ResponseEntity.ok().build();
    }
    
    /*
     * Загружает следующие 10 сообщений если пользователь 
     * свайпает переписку наверх  
     */
    @GetMapping("/omessages")
    public ResponseEntity getMessagesByChatId(@RequestParam("chatId") Long chatId){ 
        // ...
        return ResponseEntity.ok().build();
    }
    
}
