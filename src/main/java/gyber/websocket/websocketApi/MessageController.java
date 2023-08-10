package gyber.websocket.websocketApi;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import gyber.websocket.messageConfig.Message;

@Controller
public class MessageController {






   @MessageMapping("/chat")
   @SendTo("/topic/messages")
    public Message send(Message message) throws Exception {
        // String time = new SimpleDateFormat("HH:mm").format(new Date());
        return message;
    }   


    @MessageMapping("/chacngekey")
    public Message sendTo(Message message){

        return null;

    }

    
}
