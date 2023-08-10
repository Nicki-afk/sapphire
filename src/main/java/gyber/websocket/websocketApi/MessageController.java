package gyber.websocket.websocketApi;

import java.text.SimpleDateFormat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import gyber.websocket.messageConfig.Message;

@Controller
public class MessageController {






   @MessageMapping("/chat")
   @SendTo("/topic/messages")
    public Message send(Message message) throws Exception {
        // String time = new SimpleDateFormat("HH:mm").format(new Date());
        return message;
    }   

    
}
