package gyber.sapphire.entities;

import java.time.LocalDateTime;
import java.util.Queue;

import javax.persistence.*;
import javax.validation.Valid;

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

    @Column(name = "time_to_create") private LocalDateTime dateAndTimeToCreateChat;

    @Column(name = "companion_one") private Long user1;
    @Column(name = "companion_two") private Long user2;

    // @ManyToOne @Valid private User userOne;
    // @ManyToOne @Valid private User userTwo;


    // private String nickNamePersonOne;
    // private String nickNamePersonTwo;
   // private Queue<Message> queueMessages;

    
}
