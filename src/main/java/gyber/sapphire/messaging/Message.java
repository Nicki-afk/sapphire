package gyber.sapphire.messaging;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "msg_id")
    private Long msgID;


    @ManyToOne
    private User snederId;

 
    @Column(name = "message_hash")
    @NotBlank 
    private String content;

    @Column @NotNull private LocalDateTime sentAt;


    @ManyToOne
    @JoinColumn(name = "chat" , nullable = false)
    private Chat chat;


    public Message(@NotBlank String content, Chat chat) {
        this.content = content;
        this.chat = chat;
    }


    public Message(@NotBlank String content) {
        this.content = content;
    }


}