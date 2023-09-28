package gyber.sapphire.exceptions;

import gyber.sapphire.entities.User;
import gyber.sapphire.security.authenticate.tokenManagement.TokenPairObject;
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
