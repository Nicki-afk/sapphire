package gyber.websocket.security.authenticate.signature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.refEq;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


public class SignatureCheckerTest {






    public Stream<String[]> getMoreParams(){




        return Stream.of(
            getArrayStringInBase64EncodingValues("0x123c8fe2d95d8fba14dd1e7bbe9da6e717d77d254920bbb4cd5d8dd57e2988202de51b9b00e61b2a9b3ad6e2087f3b8ec14514fe70ac19b42d866b82d13dfc421b", "This is A Simple authorization message", "0x7e72Dc9D21f749Eb7E669Eb834f9b112AD9Babec") , 
            getArrayStringInBase64EncodingValues("0x2a01333943bcb3903d8bcd70701ead3133624c194d2571b22e169a8b35ad6767783f7a0c3643f822edeb3f9f378610dd700ce6ffbf88a366ab808036c16415911c", "This is A Simple authorization message two", "0x7e72Dc9D21f749Eb7E669Eb834f9b112AD9Babec") ,  
            getArrayStringInBase64EncodingValues("0x5e6a8ddce31a62de8ae63cd2789befbc82dacebbb39dfd004ce9996667ae0dba17052569d1906b891f2cc20de362cdc35a52891ec36d78aa2859765213819c3c1b", "This is A Simple authorization message three", "0x7e72Dc9D21f749Eb7E669Eb834f9b112AD9Babec") ,  
            getArrayStringInBase64EncodingValues("0x363ce66c917a1333447f8d150cfebec8c3bddc0d5a1945a29e5d061603cc0d5438703f8973bcb7b545d898a51243fd20021e40bcdfc41181fe263b6209cc9b831c", "This is A Simple authorization message for", "0x7e72Dc9D21f749Eb7E669Eb834f9b112AD9Babec") 
        ); 

    }


    public String[] getArrayStringInBase64EncodingValues(String signa , String message , String address){

        String messageToPrefix = "\u0019Ethereum Signed Message:\n" + message.length() + message;
        return new String[]{
            (Base64.getEncoder().encodeToString(signa.getBytes())) , 
            (Base64.getEncoder().encodeToString(messageToPrefix.getBytes())) , 
            (Base64.getEncoder().encodeToString(address.getBytes()))
        };


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
