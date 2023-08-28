package gyber.websocket.security.authenticate.tokenManagement;

import gyber.websocket.models.UserCustomDetails;
import gyber.websocket.models.User;

public interface TokenAuthenticate {
    default String createToken(UserCustomDetails userIPFSDetails){ return "";};
    default String createToken(){ return "";};
    boolean validateToken(String token);
}
