package gyber.websocket.security.authenticate;

import gyber.websocket.models.UserIPFSDetails;
import gyber.websocket.models.UserIPFSModel;

public interface TokenAuthenticate {
    String createToken(UserIPFSDetails userIPFSDetails);
    boolean validateToken(String token);
}
