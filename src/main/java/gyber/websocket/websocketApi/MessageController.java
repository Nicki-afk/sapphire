package gyber.websocket.websocketApi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import gyber.websocket.messageConfig.KeyExchangeSystemMessage;
import gyber.websocket.messageConfig.Message;

@Controller
public class MessageController {


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;





   @MessageMapping("/chat")
   @SendTo("/topic/messages")
    public Message send(Message message) throws Exception {
        // String time = new SimpleDateFormat("HH:mm").format(new Date());
        return message;
    }   

ш
    @MessageMapping("/p2p")
    public void sendToUser(@Payload Message message){
        String userChanel = "/app/p2p/".concat(message.getTo());
        this.simpMessagingTemplate.convertAndSend(userChanel, message);
        System.out.println("Message sent succesful");
    }


    

    
}
