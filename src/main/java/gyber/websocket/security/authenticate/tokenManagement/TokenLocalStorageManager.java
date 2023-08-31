package gyber.websocket.security.authenticate.tokenManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gyber.websocket.models.User;
import gyber.websocket.models.UserCustomDetails;

@Service
public class TokenLocalStorageManager {
    private Map<User , TokenPairObject>userAndHisTokensPair = new HashMap<>();

    @Autowired private JwtService jwtService;
    @Autowired private RTService refreshTokenService;


    public TokenPairObject addTokenPairForUser(User user){

        TokenPairObject tokenPairUser = new TokenPairObject(this.jwtService.createToken(new UserCustomDetails(user)), this.refreshTokenService.createToken());
        this.userAndHisTokensPair.put(user, tokenPairUser);
        
        return tokenPairUser;
    }

    public boolean isRefreshTokenBelongsThisUser(User user){
        return false;
    }

    public boolean isRefreshTokenBelongsThisUser(Long userId){
        return false;
    }

    public void updateTokenPairUser(User user){

        UserCustomDetails userCustomDetails = new UserCustomDetails(user);
        TokenPairObject newTokenPairObject = new TokenPairObject(this.jwtService.createToken(userCustomDetails), this.refreshTokenService.createToken());
        this.userAndHisTokensPair.replace(user, this.userAndHisTokensPair.get(user), newTokenPairObject);

    }

    public boolean exisistRefresh(String refresh){

        Set<User>userKeySet = this.userAndHisTokensPair.keySet();
        for(User user : userKeySet){
            if(this.userAndHisTokensPair.get(user).getRefreshToken().equals(refresh)){
                return true;
            }

        }

        return false;


    }

    public User getUserByRefresh(String refresh){

        Set<User>userKeySet = this.userAndHisTokensPair.keySet();
        for(User user : userKeySet){
            if(this.userAndHisTokensPair.get(user).getRefreshToken().equals(refresh)){
                return user;
            }

        }

        return null;
         
    }

    public User getUserByJwt(String jwt){
        return null;

    }


    public boolean existTokenPair(TokenPairObject tokenPairObject){
        return false;
    }


    public boolean jwtTokenIsValid(String jwtString){
        return this.jwtTokenIsValid(jwtString);
    }

    public boolean refreshTokenIsValid(String refreshString){

        return this.refreshTokenService.validateToken(refreshString);
    }

    


    
}
