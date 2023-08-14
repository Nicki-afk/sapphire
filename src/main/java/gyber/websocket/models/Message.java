package gyber.websocket.models;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private Long id;
    private String from;
    private String to;
    private String content;
    private LocalDateTime dateSent;
    private LocalDateTime dateReceived;
    private Boolean isRead = false;
    private Boolean isDelivered = false;

 




}