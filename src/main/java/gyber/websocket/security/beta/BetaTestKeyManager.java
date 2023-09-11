package gyber.websocket.security.beta;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gyber.websocket.exceptions.BetaTestKeyException;
import gyber.websocket.models.User;
import gyber.websocket.models.repo.BetaTestKeyRepository;
import net.bytebuddy.utility.RandomString;

@Service
public class BetaTestKeyManager {

    @Autowired
    private BetaTestKeyRepository repository;


    @Value("${tsar.key}}")
    private String TSAR_KEY;

    public BetaTestKey[] generateMoreKeys(int quantity , String tsarKey) throws BetaTestKeyException{

        if(!TSAR_KEY.equals(tsarKey)){
           throw new BetaTestKeyException("Your king tsar-does not exist. This key is not the tsar-key", null);
        }

        BetaTestKey[]moreKeys = new BetaTestKey[quantity];
        for(int x = 0; x < moreKeys.length; x++){
            String key = new RandomString(128).nextString();
            moreKeys[x] = new BetaTestKey(key);
        }

        return moreKeys;
    }


    /*
     * For test : src/test/java/gyber/websocket/security/beta/BetaTestKeyManagerTest.java
     */
    public BetaTestKey[] testGenerateMoreKeys(int quantity , String tsarKey) throws BetaTestKeyException{

        if(!TSAR_KEY.equals(tsarKey)){
           throw new BetaTestKeyException("Your king tsar-does not exist. This key is not the tsar-key", null);
        }

        BetaTestKey[]moreKeys = new BetaTestKey[quantity];
        for(int x = 0; x < moreKeys.length; x++){
            String key = new RandomString(128).nextString();
            moreKeys[x] = new BetaTestKey((LocalDateTime.now()), (LocalDateTime.now().plusMinutes(2)), false, key);
        }

        return moreKeys;
    }


    public boolean isBetaKeyIsValid(String key) throws BetaTestKeyException{
        if(!existKey(key)){
            throw new BetaTestKeyException("The given key does not exist " , null);

        }

        BetaTestKey extractedKey = this.repository.findByKey(key).orElse(null);

        if(!isExpiried(extractedKey)){
            throw new BetaTestKeyException("The key has expired, this key can no longer be used" , extractedKey);
        }



        return true;
    }


    public boolean isExpiried(BetaTestKey betaTestKey){
        return LocalDateTime.now().isBefore(betaTestKey.getExpirationDate());

    }

    public boolean isActive(BetaTestKey betaTestKey){
        return betaTestKey.isActive();

    }

    private boolean existKey(String key) throws BetaTestKeyException{
        return this.repository.existByKey(key);
    }








    
}
