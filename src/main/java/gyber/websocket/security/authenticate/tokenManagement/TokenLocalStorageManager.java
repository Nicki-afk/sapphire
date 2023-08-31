package gyber.websocket.security.authenticate.tokenManagement;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gyber.websocket.models.User;

@Service
public class TokenLocalStorageManager {
    private Map<User , TokenPairObject>userAndHisTokensPair = new HashMap<>();

    @Autowired private JwtService jwtService;
    @Autowired private RTService refreshTokenService;


    public void addTokenPairForUser(User user , TokenPairObject tokenPairUser){
        this.userAndHisTokensPair.put(user, tokenPairUser);
    }

    public boolean isRefreshTokenBelongsThisUser(User user){
        return false;
    }

    public boolean isRefreshTokenBelongsThisUser(Long userId){
        return false;
    }

    public void updateTokenPairUser(User user){

    }


    public boolean existTokenPair(TokenPairObject tokenPairObject){
        return false;
    }


    public boolean jwtTokenIsValid(String jwtString){
        return false;
    }

    public boolean refreshTokenIsValid(String refreshString){

        return this.refreshTokenService.validateToken(refreshString);
    }

    


    
}
