package gyber.websocket.exceptions;

import gyber.websocket.security.beta.BetaTestKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BetaTestKeyException extends Exception {

    private BetaTestKey betaTestKey;

    public BetaTestKeyException(String message , BetaTestKey betaTestKey){
        super(message);
        this.betaTestKey = betaTestKey;

    }
    
}
