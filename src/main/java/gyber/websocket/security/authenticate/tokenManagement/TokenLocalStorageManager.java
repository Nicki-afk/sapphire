package gyber.websocket.security.authenticate.tokenManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gyber.websocket.models.User;
import gyber.websocket.models.UserCustomDetails;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class TokenLocalStorageManager {
    private Map<User , TokenPairObject>userAndHisTokensPair = new HashMap<>();

    @Autowired private JwtService jwtService;
    @Autowired private RTService refreshTokenService;


    /*
     * Блокирует поток пока не будет произведена вставка в карту. Сделано 
     * для того что бы поддерживать актуальность данных , однако может вызвать проблемы 
     * с временем выполнения. Таким образом проверка токенов в фильтре при чтений 
     * информации может быть значительно замедленна так как поток будет заблокирован 
     */
    public synchronized TokenPairObject addTokenPairForUser(User user){

        if(user == null ){
            throw new NullPointerException("Object user is null");

        }
        TokenPairObject tokenPairUser = new TokenPairObject(this.jwtService.createToken(new UserCustomDetails(user)), this.refreshTokenService.createToken());
        this.userAndHisTokensPair.put(user, tokenPairUser);
        
        return tokenPairUser;
    }

    public boolean isRefreshTokenBelongsThisUser(User user , String refresh){

        if(user == null || refresh.isEmpty() || refresh == null){
            throw new NullPointerException("User , or token is empty or null");

        }

        if(this.userAndHisTokensPair.containsKey(user)){
            return this.userAndHisTokensPair.get(user).getRefreshToken().equals(refresh);
        }

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

        Set<User>userKeySet = this.userAndHisTokensPair.keySet();
        for(User user : userKeySet){
            if(this.userAndHisTokensPair.get(user).getJwtToken().equals(jwt)){
                return user;
            }

        }

        return null;

    }


    public boolean existTokenPair(TokenPairObject tokenPairObject){
    

        return this.userAndHisTokensPair.containsValue(tokenPairObject);
    }


    public boolean jwtTokenIsValid(String jwtString){
        return this.jwtService.validateToken(jwtString);
    }

    public boolean refreshTokenIsValid(String refreshString){

        return this.refreshTokenService.validateToken(refreshString);
    }

    


    
}
