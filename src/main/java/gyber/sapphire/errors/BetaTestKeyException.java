package gyber.sapphire.errors;

import gyber.sapphire.beta.BetaTestKey;
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

    public BetaTestKeyException(String msg){
        super(msg);

    }
    

    public BetaTestKeyException(String message , Throwable th){
        super(message , th);

    }
}
