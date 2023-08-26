package gyber.websocket.security.authenticate;

import gyber.websocket.models.UserIPFSDetails;
import gyber.websocket.models.UserIPFSModel;

public interface TokenAuthenticate {
    default String createToken(UserIPFSDetails userIPFSDetails){ return "";};
    default String createToken(){ return "";};
    boolean validateToken(String token);
}
