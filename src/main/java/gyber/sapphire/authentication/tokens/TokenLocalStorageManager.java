package gyber.sapphire.authentication.tokens;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gyber.sapphire.errors.TokenLocalStorageException;
import gyber.sapphire.profile.User;
import gyber.sapphire.profile.UserCustomDetails;
import lombok.Getter;
import lombok.Setter;

/*
 * Добавить : 
 * - Поиск пары токенов по refresh 
 * - Поиск пары токенов по jwt 
 * - existJwt()
 * - Сделать так что при обновлений токенов возвращался новый TokenPairObject
 */

@Service
public class TokenLocalStorageManager {
    private Map<User , TokenPairObject>userAndHisTokensPair = new HashMap<>();
    private static final ReadWriteLock THREAD_READ_WRITE_MANAGER = new ReentrantReadWriteLock();

    
    @Getter
    @Setter
    @Autowired 
    private JwtService jwtService;


    @Getter
    @Setter
    @Autowired 
    private RTService refreshTokenService;


    public  TokenPairObject addTokenPairForUser(User user) throws TokenLocalStorageException{

        if(!objectUserIsValid(user)){
            throw new TokenLocalStorageException("Error mapping user data, mapping unit == { User object } is null" , new NullPointerException("User object is null"));

        }


        this.THREAD_READ_WRITE_MANAGER.writeLock().lock();
        try{


            TokenPairObject tokenPairUser = new TokenPairObject(this.jwtService.createToken(new UserCustomDetails(user)), this.refreshTokenService.createToken());
            this.userAndHisTokensPair.put(user, tokenPairUser);
            return tokenPairUser;
        }finally{
            this.THREAD_READ_WRITE_MANAGER.writeLock().unlock();
        }
        

    }


    /*
     * @nic_ko : Добавить проверку existRefreshToken() перед выполнением основного тела метода 
     */
   
    public boolean isRefreshTokenBelongsThisUser(User user , String refresh) throws TokenLocalStorageException{

        this.THREAD_READ_WRITE_MANAGER.readLock().lock();

        try{
            if(refresh.isEmpty() || refresh == null){
                throw new TokenLocalStorageException("Error mapping user data, mapping unit == refresh token is empty or null" , new NullPointerException("User refresh is null or empty"));

            }else if (!objectUserIsValid(user)){
                throw new TokenLocalStorageException("Error mapping user data, mapping unit == { User object } is null" , new NullPointerException("User object is null"));

            }


            return this.userAndHisTokensPair.entrySet().stream().anyMatch(entry -> entry.getKey().equals(user) && entry.getValue().getRefreshToken().equals(refresh));


        }finally{
            this.THREAD_READ_WRITE_MANAGER.readLock().unlock();
        }
    }

     /*
     * @nic_ko : Добавить проверку existRefreshToken() перед выполнением основного тела метода 
     *           Также добавить проверку через метод validateRefresh()
     */
    public boolean isRefreshTokenBelongsThisUser(Long userId , String refreshToken) throws TokenLocalStorageException{

        if(userId == 0 || userId == null || userId < 0){
             throw new TokenLocalStorageException("Error mapping user data, mapping unit == user id { id < 0 || id == null || id == 0 } " , new IllegalArgumentException("Invalid user ID value"));

        }else if(refreshToken == null || refreshToken.isEmpty()){
            throw new TokenLocalStorageException("Error mapping user data, mapping unit == refresh token is empty or null" , new NullPointerException("User refresh is null or empty"));

        }

        THREAD_READ_WRITE_MANAGER.readLock().lock();
        try{

            return this.userAndHisTokensPair.entrySet().stream().anyMatch(entry -> entry.getKey().getId() == userId && entry.getValue().getRefreshToken().equals(refreshToken));
        }finally{
            THREAD_READ_WRITE_MANAGER.readLock().unlock();
        }
    }

    public void updateTokenPairUser(User user) throws TokenLocalStorageException{

        if(!objectUserIsValid(user)){
            throw new TokenLocalStorageException("Unable to refresh user token because user object is null" , new NullPointerException("User object is null"));
        }

        this.THREAD_READ_WRITE_MANAGER.writeLock().lock();

        try{
            UserCustomDetails userCustomDetails = new UserCustomDetails(user);
            TokenPairObject newTokenPairObject = new TokenPairObject(this.jwtService.createToken(userCustomDetails), this.refreshTokenService.createToken());
            this.userAndHisTokensPair.replace(user, this.userAndHisTokensPair.get(user), newTokenPairObject);
            
        }finally{
            this.THREAD_READ_WRITE_MANAGER.writeLock().unlock();
        }
    }
    /*
     * @nic_ko : добавить проверку через метод validateRefresh()
     */
    public boolean exisistRefresh(String refresh) throws TokenLocalStorageException{

        if(refresh.isEmpty() || refresh == null){
            throw new TokenLocalStorageException("Error mapping user data, mapping unit == refresh token is empty or null" , new NullPointerException("User refresh is null or empty"));

        }

        this.THREAD_READ_WRITE_MANAGER.readLock().lock();
        try{
            return this.userAndHisTokensPair.entrySet().stream()
                .anyMatch(entry -> entry.getValue().getRefreshToken().equals(refresh));
        }finally{
            this.THREAD_READ_WRITE_MANAGER.readLock().unlock();
        }    
    }


     /*
     * @nic_ko : Добавить проверку existRefreshToken() перед выполнением основного тела метода 
     *           Также добавить проверку через метод validateRefresh()
     */
    public User getUserByRefresh(String refresh) throws TokenLocalStorageException{

        if(refresh.isEmpty() || refresh == null){
            throw new TokenLocalStorageException("Error mapping user data, mapping unit == refresh token is empty or null" , new NullPointerException("User refresh is null or empty"));

        }

     
        this.THREAD_READ_WRITE_MANAGER.readLock().lock();
        try{
            return this.userAndHisTokensPair.entrySet().stream().filter(entry -> entry.getValue().getRefreshToken().equals(refresh)).findFirst().get().getKey();

        }finally{
            this.THREAD_READ_WRITE_MANAGER.readLock().unlock();
        }
         
    }


    /*
     * @nic_ko : Добавить проверку existJwt() перед выполнением основного тела метода 
     */
    public User getUserByJwt(String jwt) throws TokenLocalStorageException{

        if(jwt.isEmpty() || jwt == null ){
            throw new TokenLocalStorageException("Error mapping user data, mapping unit == jwt token is empty or null" , new NullPointerException("User jwt is null or empty"));

        }else if(!this.userAndHisTokensPair.entrySet().stream().anyMatch(entry -> entry.getValue().getJwtToken().equals(jwt))){
            throw new TokenLocalStorageException("Error mapping user data, mapping unit == jwt token is not found" , new NullPointerException("User jwt is null or empty"));
        }

        this.THREAD_READ_WRITE_MANAGER.readLock().lock();
        try{
            return this.userAndHisTokensPair.entrySet().stream().filter(entry -> entry.getValue().getJwtToken().equals(jwt)).findFirst().get().getKey();

        }finally{
            this.THREAD_READ_WRITE_MANAGER.readLock().unlock();
        }

    }


    public boolean existTokenPair(TokenPairObject tokenPairObject) throws TokenLocalStorageException{

        if(!objectTokenPairIsValid(tokenPairObject)){
            throw new TokenLocalStorageException("The pair of tokens passed for processing cannot be correctly processed because it is empty" , new NullPointerException("TokenPairObject is null"));
            
        }
    
        this.THREAD_READ_WRITE_MANAGER.readLock().lock();

        try{

            return this.userAndHisTokensPair.containsValue(tokenPairObject);
        }finally{
            this.THREAD_READ_WRITE_MANAGER.readLock().unlock();
        }
    }


    /*
     * @nic_ko : Добавить проверку 
     */
    public boolean jwtTokenIsValid(String jwtString){
        return this.jwtService.validateToken(jwtString);
    }

    /*
     * @nic_ko : Добавить проверку 
     */
    public boolean refreshTokenIsValid(String refreshString){

        return this.refreshTokenService.validateToken(refreshString);
    }

    public TokenPairObject getTokenPairInUser(User user) throws TokenLocalStorageException{

            if (!objectUserIsValid(user)){
                throw new TokenLocalStorageException("Error mapping user data, mapping unit == { User object } is null" , new NullPointerException("User object is null"));

            }
        this.THREAD_READ_WRITE_MANAGER.readLock().lock();
        
        try{
            TokenPairObject tokenPairObject = this.userAndHisTokensPair.get(user);
            return tokenPairObject;

        }finally{

            this.THREAD_READ_WRITE_MANAGER.readLock().unlock();
        }

        
        

    }


    /*
     * @nic_ko : Добавить проверку 
     */
    public void deleteTokenPairUser(User userToDelete){
        this.THREAD_READ_WRITE_MANAGER.writeLock().lock();

        try{
            this.userAndHisTokensPair.remove(userToDelete);


        }finally{
            this.THREAD_READ_WRITE_MANAGER.writeLock().unlock();
        }

    }

     /*
     * @nic_ko : Добавить проверку 
     */
    public void deleteTokenPairUser(TokenPairObject tokenPairObject){

        THREAD_READ_WRITE_MANAGER.writeLock().lock();

        try{

            this.userAndHisTokensPair.entrySet().removeIf(entry -> entry.getValue().equals(tokenPairObject));
        

        }finally{
            THREAD_READ_WRITE_MANAGER.writeLock().unlock();
        }

    }




    public  Map<User, TokenPairObject> getUserAndHisTokensPair() {
     
        this.THREAD_READ_WRITE_MANAGER.readLock().lock();

        try{
            Map<User , TokenPairObject>returnedMap = this.userAndHisTokensPair;
            return returnedMap;
        }finally{
            THREAD_READ_WRITE_MANAGER.readLock().unlock();
        }

    }

    /*
     * @nic_ko : Временное решение. В будущем подключить валидацию Spring 
     */
    private boolean objectUserIsValid(User user){
        return user == null || user.getUserName().isEmpty() || user.getUserName() == null ||   
                    user.getCryptoWalletAddress().isEmpty() || user.getCryptoWalletAddress() == null || 
                                            user.getHashUserFile().isEmpty() || user.getHashUserFile() == null ? false : true;
    }



     /*
     * @nic_ko : Временное решение. В будущем подключить валидацию Spring 
     */
    private boolean objectTokenPairIsValid(TokenPairObject tokenPairObject){
        return tokenPairObject == null || tokenPairObject.getJwtToken().isEmpty() || tokenPairObject.getJwtToken() == null ||   
                        tokenPairObject.getRefreshToken().isEmpty() || tokenPairObject.getRefreshToken() == null ? false : true;
                        
    }



        
}
