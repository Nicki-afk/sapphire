package gyber.sapphire.security.beta;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gyber.sapphire.entities.User;
import gyber.sapphire.entities.repositories.BetaTestKeyRepository;
import gyber.sapphire.exceptions.BetaTestKeyException;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;

@Service
@Getter
@Setter
public class BetaTestKeyManager {

    @Autowired
    private BetaTestKeyRepository repository;


    @Value("${tsar.key}")
    private String TSAR_KEY;

    public BetaTestKey[] generateMoreKeys(int quantity , String tsarKey) throws BetaTestKeyException{

        if(!TSAR_KEY.equals(tsarKey)){
           throw new BetaTestKeyException("Your king tsar-does not exist. This key is not the tsar-key");
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
           throw new BetaTestKeyException("Your king tsar-does not exist. This key is not the tsar-key");
        }

        if(quantity <= 0 || tsarKey.isEmpty() || tsarKey == null){
            throw new BetaTestKeyException("it is impossible to generate keys because the generation parameters are not valid");
        }

        BetaTestKey[]moreKeys = new BetaTestKey[quantity];
        for(int x = 0; x < moreKeys.length; x++){
            String key = new RandomString(128).nextString();
            moreKeys[x] = new BetaTestKey((LocalDateTime.now()), (LocalDateTime.now().plusMinutes(1)), false, key);
        }

        return moreKeys;
    }

    public void saveMoreKeys(BetaTestKey[]arrBetaTestKey ) throws BetaTestKeyException{
        for(int x = 0; x < arrBetaTestKey.length; x++){

            if((this.repository.save(arrBetaTestKey[x])) == null ){
                throw new BetaTestKeyException("The key could not be saved for an unknown reason. Try examining the stack trace of the error to localize the problem."
                     , new NullPointerException("An error occurred while trying to save the key to the database. The return value should be a key but it is null"));
                     
            }
            
            
        }

    }


    public boolean isBetaKeyIsValid(String key) throws BetaTestKeyException{
        if(!existsKey(key)){
            throw new BetaTestKeyException("The given key does not exist ");

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

    private boolean existsKey(String key) throws BetaTestKeyException{
        return this.repository.existsByKey(key);
    }








    
}
