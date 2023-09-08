package gyber.websocket.security.beta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gyber.websocket.models.User;
import gyber.websocket.models.repo.BetaTestKeyRepository;
import net.bytebuddy.utility.RandomString;

@Service
public class BetaTestKeyManager {

    @Autowired
    private BetaTestKeyRepository repository;


    @Value("${tsar.key}}")
    private String TSAR_KEY;

    public BetaTestKey[] generateMoreKeys(int quantity , String tsarKey){

        if(!TSAR_KEY.equals(tsarKey)){
            // throws CustomException
        }

        BetaTestKey[]moreKeys = new BetaTestKey[quantity];
        for(int x = 0; x < moreKeys.length; x++){
            String key = new RandomString(128).nextString();
            moreKeys[x] = new BetaTestKey(key);
        }

        return moreKeys;
    }


    public boolean isBetaKeyIsValid(BetaTestKey betaTestKey){
        // isActive()
        // existKey()
        return false;
    }

    private boolean isActive(BetaTestKey betaTestKey){
        return false;

    }

    private boolean existKey(BetaTestKey betaTestKey){
        return false;
    }








    
}
