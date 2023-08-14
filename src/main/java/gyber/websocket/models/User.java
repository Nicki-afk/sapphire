package gyber.websocket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String userName;
    private String firstName;
    private String lastName;
    private String metamaskKey;
    private String betaTestKey;
    private String passwd;

    
}
