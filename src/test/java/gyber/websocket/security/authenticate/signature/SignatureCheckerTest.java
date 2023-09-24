package gyber.websocket.security.authenticate.signature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.refEq;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.apache.el.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


public class SignatureCheckerTest {






    public Stream getMoreParams(){

        return null; 

    }






    @Test
    public void testVerifySignature() throws UnsupportedEncodingException {


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

        boolean result = checker.verifySignature();


        assertTrue(result);

    }


    @Test
    public void testVerifySignatureInInvalidSignature(){

    }


    @ParameterizedTest
    @MethodSource("getMoreParams")
    public void testVerifySignatureInMoreParameters(){
        
    }
}
