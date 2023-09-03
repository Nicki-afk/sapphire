package gyber.websocket.security.authenticate.tokenManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gyber.websocket.models.User;
import gyber.websocket.models.UserCustomDetails;
import lombok.Getter;
import lombok.Setter;


/*
 * TODO : Добавить удаление токенов пользователя
 *        Отредактировать свойство ReadWriteLock -> сделать статичным и сделать константой
 */
@Service
public class TokenLocalStorageManager {
    private Map<User , TokenPairObject>userAndHisTokensPair = new HashMap<>();
    private ReadWriteLock reaaReadWriteLock = new ReentrantReadWriteLock();

    
    @Getter
    @Setter
    @Autowired 
    private JwtService jwtService;


    @Getter
    @Setter
    @Autowired 
    private RTService refreshTokenService;


    public  TokenPairObject addTokenPairForUser(User user){


        this.reaaReadWriteLock.writeLock().lock();
        try{
            TokenPairObject tokenPairUser = new TokenPairObject(this.jwtService.createToken(new UserCustomDetails(user)), this.refreshTokenService.createToken());
            this.userAndHisTokensPair.put(user, tokenPairUser);
            return tokenPairUser;
        }finally{
            this.reaaReadWriteLock.writeLock().unlock();
        }
        

    }


    /*
     * TODO : Оптимизировать под Stream<>
     */
    public boolean isRefreshTokenBelongsThisUser(User user , String refresh){

        this.reaaReadWriteLock.readLock().lock();

        try{
            if(user == null || refresh.isEmpty() || refresh == null){
                throw new NullPointerException("User , or token is empty or null");

            }

            if(this.userAndHisTokensPair.containsKey(user)){
                return this.userAndHisTokensPair.get(user).getRefreshToken().equals(refresh);
            }

            return false;

        }finally{
            this.reaaReadWriteLock.readLock().unlock();
        }
    }

     /*
     * TODO : Дописать 
     */
    public boolean isRefreshTokenBelongsThisUser(Long userId){
        return false;
    }

    public void updateTokenPairUser(User user){

        this.reaaReadWriteLock.writeLock().lock();

        try{
            UserCustomDetails userCustomDetails = new UserCustomDetails(user);
            TokenPairObject newTokenPairObject = new TokenPairObject(this.jwtService.createToken(userCustomDetails), this.refreshTokenService.createToken());
            this.userAndHisTokensPair.replace(user, this.userAndHisTokensPair.get(user), newTokenPairObject);
            
        }finally{
            this.reaaReadWriteLock.writeLock().unlock();
        }
    }

    public boolean exisistRefresh(String refresh){

        this.reaaReadWriteLock.readLock().lock();
        try{
            return this.userAndHisTokensPair.entrySet().stream()
                .anyMatch(entry -> entry.getValue().getRefreshToken().equals(refresh));
        }finally{
            this.reaaReadWriteLock.readLock().unlock();
        }    
    }

    public User getUserByRefresh(String refresh){

     
        this.reaaReadWriteLock.readLock().lock();
        try{
            return this.userAndHisTokensPair.entrySet().stream().filter(entry -> entry.getValue().getRefreshToken().equals(refresh)).findFirst().get().getKey();

        }finally{
            this.reaaReadWriteLock.readLock().unlock();
        }
         
    }

    public User getUserByJwt(String jwt){

        this.reaaReadWriteLock.readLock().lock();
        try{
            return this.userAndHisTokensPair.entrySet().stream().filter(entry -> entry.getValue().getJwtToken().equals(jwt)).findFirst().get().getKey();

        }finally{
            this.reaaReadWriteLock.readLock().unlock();
        }

    }


    public boolean existTokenPair(TokenPairObject tokenPairObject){
    
        this.reaaReadWriteLock.readLock().lock();

        try{

            return this.userAndHisTokensPair.containsValue(tokenPairObject);
        }finally{
            this.reaaReadWriteLock.readLock().unlock();
        }
    }


    public boolean jwtTokenIsValid(String jwtString){
        return this.jwtService.validateToken(jwtString);
    }

    public boolean refreshTokenIsValid(String refreshString){

        return this.refreshTokenService.validateToken(refreshString);
    }

    public TokenPairObject getTokenPairInUser(User user){

        this.reaaReadWriteLock.readLock().lock();
        
        try{
            TokenPairObject tokenPairObject = this.userAndHisTokensPair.get(user);
            return tokenPairObject;

        }finally{

            this.reaaReadWriteLock.readLock().unlock();
        }

        
        

    }




    public  Map<User, TokenPairObject> getUserAndHisTokensPair() {
     
        this.reaaReadWriteLock.readLock().lock();

        try{
            Map<User , TokenPairObject>returnedMap = this.userAndHisTokensPair;
            return returnedMap;
        }finally{
            reaaReadWriteLock.readLock().unlock();
        }

    }

        
}
