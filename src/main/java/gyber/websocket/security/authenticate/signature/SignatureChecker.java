package gyber.websocket.security.authenticate.signature;

import java.math.BigInteger;
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
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

public class SignatureChecker {




    public static boolean verifySignature(String address , String signature , String base64MessageHash) {

        try{
        byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
        byte[] r = new byte[32];
        byte[] s = new byte[32];
        byte v = signatureBytes[64];
        

        String decodeMsg = new String(Base64.getDecoder().decode(base64MessageHash));

    
        // Копируем байты в соответствующие массивы
        System.arraycopy(signatureBytes, 0, r, 0, 32);
        System.arraycopy(signatureBytes, 32, s, 0, 32);

         Sign.SignatureData signatureData = new Sign.SignatureData(v, r, s);

            BigInteger pubKey = Sign.signedMessageToKey(decodeMsg.getBytes(), signatureData);

            String computedAddress = "0x" + Keys.getAddress(pubKey);
            if (computedAddress.equalsIgnoreCase(address)) {
                return true;
            } else {
                return false;
            }


        }catch(Exception e){
            e.printStackTrace();

        }
       
       


        return false;
    }



}
