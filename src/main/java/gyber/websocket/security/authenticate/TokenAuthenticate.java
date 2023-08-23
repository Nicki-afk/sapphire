package gyber.websocket.security.authenticate;

import gyber.websocket.models.UserIPFSModel;

public interface TokenAuthenticate {
    String createToken(UserIPFSModel userIPFSModel);
    boolean validateToken(String token);
}
