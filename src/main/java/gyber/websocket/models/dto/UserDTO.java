package gyber.websocket.models.dto;

import gyber.websocket.models.NetStatus;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    @Enumerated(EnumType.STRING)
    private NetStatus onlineStatus;
    
    private String ipfsDataHash;


    
}
