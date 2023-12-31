package gyber.sapphire.authentication.signature;

import java.io.UnsupportedEncodingException;
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
import lombok.ToString;

/*
 * TODO : Добавить валидацию на все методы касающихся подписи 
 * 
 * - Проверка на длину байтов , она должна быть строго верна 65
 * - Проверка на декодирование Base64 , корректна ли выходная строка 
 * - Прочие проверки ...  
 * 
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SignatureChecker {

    private  String inputAddress;
    private  String inputSignature;
    private  String inputMessage;





    private byte[]singBytes;
    private byte[]R;
    private byte[]S;
    private byte V;


   // private String message;
    private Sign.SignatureData signatureData;
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

      return walletAddress.equalsIgnoreCase(inputAddress);
    }

    public static SignatureCheckerBuilder builder(){

        return new SignatureCheckerBuilder();

    }







    // TODO: Community Discussion Required
    // The Builder pattern is used here, but there's a question whether it is the most appropriate choice.
    // Would chaining be a better alternative for this use case?
    // Open for community input to weigh pros and cons.
    public  static class SignatureCheckerBuilder{

        private SignatureChecker signatureChecker = new SignatureChecker();

        public SignatureCheckerBuilder setInputBase64Signature(String signatureBase64) throws UnsupportedEncodingException{

            if(signatureBase64.isEmpty() || signatureBase64 == null){
                throw new NullPointerException("signature is null or empty");
            }

            String decode = decode(signatureBase64);
            this.signatureChecker.setInputSignature(decode);
            this.signatureChecker.setSingBytes((Numeric.hexStringToByteArray(decode)));
        
            
            return this;
        }

        public SignatureCheckerBuilder setInputBase64Message(String message) throws UnsupportedEncodingException{


            this.signatureChecker.setInputMessage(decode(message));

            return this;
        }

        public SignatureCheckerBuilder setInputBase64WalletAddress(String wallet) throws UnsupportedEncodingException{

            this.signatureChecker.setInputAddress(decode(wallet));

            return this;
        }

        public SignatureCheckerBuilder copyBytes(){

            this.signatureChecker.setR(new byte[32]);
            this.signatureChecker.setS(new byte[32]);
            this.signatureChecker.setV((this.signatureChecker.getSingBytes()[64]));


            System.arraycopy(this.signatureChecker.getSingBytes(), 0, this.signatureChecker.getR(), 0, 32);
            System.arraycopy(this.signatureChecker.getSingBytes() , 32, this.signatureChecker.getS() , 0, 32);
 


            return this; 
        }


        public SignatureCheckerBuilder createSignatureObject(){


            Sign.SignatureData sign = new Sign.SignatureData(this.signatureChecker.getV(), this.signatureChecker.getR(), this.signatureChecker.getS()); 
            this.signatureChecker.setSignatureData(sign);

            return this;
        }


        public SignatureCheckerBuilder recoveryPublicKey(){

            try {

           
                BigInteger pubKey = Sign.signedMessageToKey((this.signatureChecker.getInputMessage().getBytes()) , this.signatureChecker.getSignatureData());
                this.signatureChecker.setPublicKey(pubKey);

            } catch (SignatureException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }

            
         

            return this;
        }

        public SignatureCheckerBuilder recoveryWalletAddress(){

             String computedAddress = "0x" + Keys.getAddress(this.signatureChecker.getPublicKey());
             this.signatureChecker.setWalletAddress(computedAddress);
        

            return this;
        }

        public SignatureChecker build(){
             
            return this.signatureChecker;

        }

        private String decode(String base64Data) throws UnsupportedEncodingException{

            return new String(Base64.getDecoder().decode(base64Data.getBytes("UTF-8")));

        }





    }



}
