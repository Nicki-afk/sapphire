package gyber.websocket.security.authenticate.tokenManagement;

import java.util.HashMap;
import java.util.Map;

import gyber.websocket.models.User;

public class TokenLocalStorage {


    private Map<User , TokenPairObject>userAndHisTokensPair = new HashMap<>();


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

    

    
}
