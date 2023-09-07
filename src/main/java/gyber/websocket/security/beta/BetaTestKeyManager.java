package gyber.websocket.security.beta;

import org.springframework.stereotype.Service;

import gyber.websocket.models.User;
import net.bytebuddy.utility.RandomString;

@Service
public class BetaTestKeyManager {
    

    public BetaTestKey[] generateMoreKeys(int quantity){

        BetaTestKey[]moreKeys = new BetaTestKey[quantity];
        for(int x = 0; x < moreKeys.length; x++){
            String key = new RandomString(128).nextString();
            moreKeys[x] = new BetaTestKey(key);
        }

        return moreKeys;
    }


    public boolean isBetaKeyIsValid(BetaTestKey betaTestKey){
        return false;
    }




    
}
