package gyber.websocket.security.authenticate.signature;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class SignatureChecker {


    private String algorithm = "SHA256withECDSA";

    
    @PostConstruct
    public void initComponent(){
        /// ... 
    }





    public boolean verifySignature(String base64AuthenticateData){
        return false;
    }


    public byte[] obtainSignature(String decodeBase64String){

        return new byte[]{};

    }

    public byte[] obtainPublicKey(String decodeBase64String){

        return new byte[]{};
    }

    public byte[] obtainWallet(String decodeBase64String){
        
        return new byte[]{};
    }
    
}
