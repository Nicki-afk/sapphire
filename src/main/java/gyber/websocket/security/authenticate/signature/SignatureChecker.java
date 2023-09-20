package gyber.websocket.security.authenticate.signature;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
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





    public boolean verifySignature(String base64AuthenticateData , String message) {

        byte[] publicKeyBytes = obtainPublicKey(base64AuthenticateData);
        byte[] signatureBytes = obtainSignature(base64AuthenticateData);


        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("EC");
            PublicKey publicKey = keyFactory.generatePublic(spec);


            Signature signature = Signature.getInstance(algorithm); 
            signature.initVerify(publicKey);
            signature.update(message.getBytes("UTF-8"));

            return signature.verify(signatureBytes);

        } catch (Exception e) {
           
            e.printStackTrace();

        }
       


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
