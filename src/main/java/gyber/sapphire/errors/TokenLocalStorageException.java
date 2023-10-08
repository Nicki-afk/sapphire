package gyber.sapphire.errors;

import gyber.sapphire.authentication.tokens.TokenPairObject;
import gyber.sapphire.profile.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenLocalStorageException extends Exception{

    private User user;
    private TokenPairObject tokenPairObject;


    public TokenLocalStorageException(String message){
        super(message);
    }

    public TokenLocalStorageException(String message , Throwable th){
        super(message, th);

    }

    public TokenLocalStorageException(String message , Throwable th , User user , TokenPairObject tokenPairObject){
        super(message, th);
        this.tokenPairObject = tokenPairObject;
        this.user = user;
    }


    
    
}
