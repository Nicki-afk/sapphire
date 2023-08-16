package gyber.websocket.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String metamaskKey;
    private String betaTestKey;
    private String passwd;
    private NetStatus onlineStatus;

    private List<Chat>userChats;
    

    
}
