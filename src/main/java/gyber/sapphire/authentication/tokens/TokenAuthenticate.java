package gyber.sapphire.authentication.tokens;

import gyber.sapphire.profile.User;

public interface TokenAuthenticate {
    // default String createToken(UserCustomDetails userIPFSDetails){ return "";};
    default String createToken(){ return "";};
    boolean validateToken(String token);
}
