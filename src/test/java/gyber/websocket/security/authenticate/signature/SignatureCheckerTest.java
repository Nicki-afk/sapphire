package gyber.websocket.security.authenticate.signature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


public class SignatureCheckerTest {



    @Test
    public void testVerifySignature() {

        // try {
        //     String sig = "0x5942c9b4572c9342a5294223ed71cc0c6b6d87b50b8c487545535aa6a1fe380f79f36db9475d4255e99c27461213d8b6ade22819ab227a4a6607a102aaf7a6cb1b";
        //     String message = "\u0019Ethereum Signed Message:\n" + "Hello World".length() + "Hello World";
        //     String address = "0x28161A5F4A32C67fFb17e9bA722ddB8B342A970c";

        //     byte[] signatureBytes = Numeric.hexStringToByteArray(sig);

        //     byte[] r = new byte[32];
        //     byte[] s = new byte[32];
        //     byte v = signatureBytes[64];

        //     System.arraycopy(signatureBytes, 0, r, 0, 32);
        //     System.arraycopy(signatureBytes, 32, s, 0, 32);

        //     Sign.SignatureData signatureData = new Sign.SignatureData(v, r, s);

        //     BigInteger pubKey = Sign.signedMessageToKey(message.getBytes(), signatureData);

        //     String computedAddress = "0x" + Keys.getAddress(pubKey);
        //     if (computedAddress.equalsIgnoreCase(address)) {
        //         System.out.println("Signature verified!");
        //     } else {
        //         System.out.println("Invalid signature!");
        //     }

        // } catch (Exception e) {
        //     e.printStackTrace();
        // }   


        assertTrue(

        
            SignatureChecker.verifySignature(
             "0x28161A5F4A32C67fFb17e9bA722ddB8B342A970c" , 
             "0x5942c9b4572c9342a5294223ed71cc0c6b6d87b50b8c487545535aa6a1fe380f79f36db9475d4255e99c27461213d8b6ade22819ab227a4a6607a102aaf7a6cb1b" , 
             (Base64.getEncoder().encodeToString(("\u0019Ethereum Signed Message:\n" + "Hello World".length() + "Hello World").getBytes()))    
            
            )
        );
        

    }
}
