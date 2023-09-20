package gyber.websocket.security.authenticate.signature;

import org.springframework.stereotype.Service;

@Service
public class SignatureChecker {


    public boolean verifySignature(String publicBase64key , String singBase64){
        return false;
    }
    
}
