package gyber.sapphire.messaging;

import java.time.LocalDateTime;
import java.util.Queue;
import java.util.Set;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "chats")
public class Chat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatId;

    @Column(name = "time_to_create") @NotNull private LocalDateTime dateAndTimeToCreateChat;

    @Column(name = "companion_one") @NotNull @Min(1) private Long user1;
    @Column(name = "companion_two") @NotNull @Min(1) private Long user2;

    @OneToMany(mappedBy = "chat")
    private Set<Message>messageList;



    // @ManyToOne @Valid private User userOne;
    // @ManyToOne @Valid private User userTwo;


    // private String nickNamePersonOne;
    // private String nickNamePersonTwo;
   // private Queue<Message> queueMessages;

    
}
