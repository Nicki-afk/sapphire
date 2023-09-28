package gyber.sapphire.security.authenticate.tokenManagement;

import gyber.sapphire.entities.User;
import gyber.sapphire.entities.UserCustomDetails;

public interface TokenAuthenticate {
    default String createToken(UserCustomDetails userIPFSDetails){ return "";};
    default String createToken(){ return "";};
    boolean validateToken(String token);
}
