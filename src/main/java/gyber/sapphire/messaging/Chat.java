package gyber.sapphire.messaging;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import gyber.sapphire.profile.User;
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

    @Column(name = "create_at") @NotNull private LocalDateTime dateAndTimeToCreateChat;


    @Column(name = "chat_type")
    @Enumerated(EnumType.STRING)
    private ChatType chatType;

    @ManyToMany
    private List<User> participantsChat;

    @Column(name = "chat_name")
    @NotBlank
    private String nameChat;
    

    public Chat(ChatType chatType, @NotBlank String nameChat) {
        this.chatType = chatType;
        this.nameChat = nameChat;
    }

    public Chat(ChatType chatType, List<User> participantsChat, @NotBlank String nameChat) {
        this.chatType = chatType;
        this.participantsChat = participantsChat;
        this.nameChat = nameChat;
    }

    
    
}
