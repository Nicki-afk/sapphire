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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class SignatureChecker {

    // private static String address;
    // private static String signature;
    // private static String message;
    private byte[]singBytes;
    private byte[]R;
    private byte[]S;
    private byte[]V;
    private Signature signature;
    private BigInteger publicKey;
    private String walletAddress;
 








    public static boolean verifySignature(String address , String signature , String base64MessageHash) {

        try{

        // Преобразуем подпись из шестнадцатеричной в байтовый массив
        byte[] signatureBytes = Numeric.hexStringToByteArray(signature);

          // Выделяем r, s и v из подписи
        byte[] r = new byte[32];
        byte[] s = new byte[32];
        byte v = signatureBytes[64];
        

        // Исходное сообщение с префиксом, который обычно добавляется MetaMask декодированое Base64
        String decodeMsg = new String(Base64.getDecoder().decode(base64MessageHash));

    
        // Копируем байты в соответствующие массивы
        System.arraycopy(signatureBytes, 0, r, 0, 32);
        System.arraycopy(signatureBytes, 32, s, 0, 32);
 
        // Создаем объект подписи
        Sign.SignatureData signatureData = new Sign.SignatureData(v, r, s);

        // Восстанавливаем публичный ключ из подписи и сообщения
        BigInteger pubKey = Sign.signedMessageToKey(decodeMsg.getBytes(), signatureData);

        // Получаем адрес из публичного ключа
        String computedAddress = "0x" + Keys.getAddress(pubKey);
        
        return computedAddress.equalsIgnoreCase(address);


        }catch(Exception e){
            e.printStackTrace();

        }
       
       


        return false;
    }

    public boolean verifySignature(){

        return false;
    }

    public static SignatureCheckerBuilder builder(){

        return new SignatureCheckerBuilder();

    }







    private static class SignatureCheckerBuilder{

        private SignatureChecker signatureChecker = new SignatureChecker();

        public SignatureCheckerBuilder setBase64Signature(String signatureBase64){
            
            
            return this;
        }

        public SignatureCheckerBuilder setBase64Message(String message){

            return this;
        }

        public SignatureCheckerBuilder setBase64WalletAddress(String wallet){

            return this;
        }

        public SignatureCheckerBuilder copyBytes(){

            return this; 
        }


        public SignatureCheckerBuilder createSignatureObject(){

            return this;
        }


        public SignatureCheckerBuilder recoveryPublicKey(){

            return this;
        }

        public SignatureCheckerBuilder recoveryWalletAddress(){

            return this;
        }

        public SignatureChecker verify(){
             
            return this.signatureChecker;

        }





    }



}
