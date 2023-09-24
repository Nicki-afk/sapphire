package gyber.websocket.security.authenticate.signature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


public class SignatureCheckerTest {



    @Test
    public void testVerifySignature() throws UnsupportedEncodingException {

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


        // assertTrue(

        
        //     SignatureChecker.verifySignature(
        //      "0x28161A5F4A32C67fFb17e9bA722ddB8B342A970c" , 
        //      "0x5942c9b4572c9342a5294223ed71cc0c6b6d87b50b8c487545535aa6a1fe380f79f36db9475d4255e99c27461213d8b6ade22819ab227a4a6607a102aaf7a6cb1b" , 
        //      (Base64.getEncoder().encodeToString(("\u0019Ethereum Signed Message:\n" + "Hello World".length() + "Hello World").getBytes()))    
            
        //     )
        // );

        String base64Signature = "MHhlY2FmZmNlMjliMGM4MjFlOTRmNDkzNzgxODk4MWUzMjYyNzI3ZmE2Mzg0OTMwNTI2NTFmMzk4YzZkMzEzMWI0NjZjODUxMmQxYmYwZWVkNDFmOTg3OGRlMDJmZjM0YmYzNmMwMTRmZTRjZmNmNzAyZTIwNjUyZTRiMDg2MzIxODFj";
        String message = "\u0019Ethereum Signed Message:\n" + "Hello World".length() + "Hello World";
        String base64Message = Base64.getEncoder().encodeToString(message.getBytes("UTF-8"));
        

        String base64Address = "MHg3ZTcyRGM5RDIxZjc0OUViN0U2NjlFYjgzNGY5YjExMkFEOUJhYmVj";

       SignatureChecker checker = SignatureChecker
                                .builder()  
                                .setInputBase64Signature(base64Signature)
                                .setInputBase64Message(base64Message)
                                .setInputBase64WalletAddress(base64Address)
                                .copyBytes()
                                .createSignatureObject()
                                .recoveryPublicKey()
                                .recoveryWalletAddress()
                                .build();

                               //System.out.println(checker);
                                System.out.println("2 : "  + SignatureChecker.verifySignature("0x7e72Dc9D21f749Eb7E669Eb834f9b112AD9Babec" , "0xecaffce29b0c821e94f4937818981e3262727fa638493052651f398c6d3131b466c8512d1bf0eed41f9878de02ff34bf36c014fe4cfcf702e20652e4b08632181c" , base64Message) );

       // assertTrue(result);

       System.out.println("Result : " + checker.verifySignature());
        

    }
}
