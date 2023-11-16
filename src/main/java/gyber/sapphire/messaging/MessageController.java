package gyber.sapphire.messaging;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/tohimself")
    public void sendToHimself(Message message){
       

    }

 
    // TODO : После добавления Spring Security изменить метод на convertAndSendToUser()
    @MessageMapping("/p2p")
    public void sendToUser(@Payload Message message){
       
    }


    

    
}
