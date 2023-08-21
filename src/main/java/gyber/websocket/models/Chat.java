package gyber.websocket.models;

import java.time.LocalDateTime;
import java.util.Queue;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Chat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatId;

    private LocalDateTime dateAndTimeToCreateChat;

    private String nickNamePersonOne;
    private String nickNamePersonTwo;
   // private Queue<Message> queueMessages;

    
}
