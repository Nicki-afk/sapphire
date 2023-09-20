package gyber.websocket.security.authenticate.signature;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {SignatureChecker.class})
public class SignatureCheckerTest {

    @Autowired
    private SignatureChecker signatureChecker;



    @Test
    public void testInitComponent() {
        

    }

    @Test
    public void testObtainPublicKey() {

        byte[] testArrByte = signatureChecker.obtainPublicKey("null");
        

    }

    @Test
    public void testObtainSignature() {

    }

    @Test
    public void testObtainWallet() {

    }

    @Test
    public void testVerifySignature() {

    }
}
