package gyber.sapphire.messaging;

import java.time.LocalDateTime;
import java.util.Queue;
import java.util.Set;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @Column(name = "time_to_create") @NotNull private LocalDateTime dateAndTimeToCreateChat;

    @Column(name = "companion_one") @NotBlank private String user1;
    @Column(name = "companion_two") @NotBlank private String user2;

    @OneToMany(mappedBy = "message_list" , fetch = FetchType.LAZY)
    private Set<Message>messageList;


    @ManyToOne
    @JoinColumn(name = "userchat" , nullable = false)
    private User userChats;




    
}
