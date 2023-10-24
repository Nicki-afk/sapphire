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
import javax.validation.constraints.Size;

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
    private Long id;

    @Column(name = "fromH") @NotBlank @Size(min = 6 , max = 8) private String from;
    @Column(name = "toH") @NotBlank @Size(min = 6 , max = 8) private String to;

    @Column(name = "message_hash")
    @NotBlank 
    private String content;

    @Column @NotNull private LocalDateTime dateSent;
    @Column @NotNull private LocalDateTime dateReceived;
    @Column @NotNull private Boolean isRead = false;
    @Column @NotNull private Boolean isDelivered = false;


    @ManyToOne
    @JoinColumn(name = "chat" , nullable = false)
    private Chat chat;

 




}