package gyber.websocket.security.authenticate.signature;

import java.util.Arrays;
import java.util.Base64;

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


    /*
     * обработать ошибки  
     * ArrayIndexOf ... 
     */
    public byte[] obtainSignature(String decodeBase64String){

 
        
        return
        (new String(Base64.getDecoder().decode(decodeBase64String))
        .split(":")[2]
        .getBytes());

    }

    public byte[] obtainPublicKey(String decodeBase64String){

          return
        (new String(Base64.getDecoder().decode(decodeBase64String))
        .split(":")[0]
        .getBytes());
    }

    public byte[] obtainWallet(String decodeBase64String){
        
          return
        (new String(Base64.getDecoder().decode(decodeBase64String))
        .split(":")[1]
        .getBytes());
    }
    
}
