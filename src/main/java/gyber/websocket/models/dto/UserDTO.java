package gyber.websocket.models.dto;

import gyber.websocket.models.NetStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Long id;

    private String userName;
    private String firstName;
    private String lastName;
    private NetStatus onlineStatus;
    

    
}
