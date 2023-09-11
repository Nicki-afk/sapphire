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


    public boolean isBetaKeyIsValid(BetaTestKey betaTestKey) throws BetaTestKeyException{
        if(!existKey(betaTestKey) || !isExpiried(betaTestKey)){
            throw new BetaTestKeyException("The given key does not exist or is not valid. Perhaps the key is expired" , betaTestKey);
            
        }

        return true;
    }


    public boolean isExpiried(BetaTestKey betaTestKey){
        return LocalDateTime.now().isBefore(betaTestKey.getExpirationDate());

    }

    public boolean isActive(BetaTestKey betaTestKey){
        return betaTestKey.isActive();

    }

    private boolean existKey(BetaTestKey betaTestKey) throws BetaTestKeyException{
        BetaTestKey findKey = repository.findById(betaTestKey.getId()).orElseThrow(() -> new BetaTestKeyException("The key was not found for this id. Try another key", null));
        return true;
    }








    
}
