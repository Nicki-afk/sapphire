package gyber.sapphire.messageControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import gyber.sapphire.entities.KeyExchangeSystemMessage;
import gyber.sapphire.entities.Message;

@Controller
public class MessageController {


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/tohimself")
    public void sendToHimself(Message message){
        String userChanel = "/app/tohimself/".concat(message.getFrom());
        this.simpMessagingTemplate.convertAndSend(userChanel, message);
        System.out.println("Message to himself sent successful");

    }

    // TODO : После добавления Spring Security изменить метод на convertAndSendToUser()
    @MessageMapping("/keyexchange")
    public void keyExchange(@Payload KeyExchangeSystemMessage keyExchangeSystemMessage){
        String userChannel = "/app/keyexchange".concat(keyExchangeSystemMessage.getTo());
        this.simpMessagingTemplate.convertAndSend(userChannel, keyExchangeSystemMessage);
        System.out.println("Message key sent succesful");
    }

    // TODO : После добавления Spring Security изменить метод на convertAndSendToUser()
    @MessageMapping("/p2p")
    public void sendToUser(@Payload Message message){
        String userChanel = "/app/p2p/".concat(message.getTo());
        this.simpMessagingTemplate.convertAndSend(userChanel, message);
        System.out.println("Message sent succesful");
    }


    

    
}
