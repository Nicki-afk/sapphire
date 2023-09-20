package gyber.websocket.security.authenticate.signature;

import static org.junit.Assert.assertEquals;

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

        assertEquals(
            "6f8cf484b241728e21e07c740167940f9b7d9b1b1979c8c38ff8e160a5a7d1d9ee81f7c62dec7f44bc31120bb62717edbc07b7b58d5faf982dda2df5537e58a1" , 
            new String((signatureChecker.obtainPublicKey("NmY4Y2Y0ODRiMjQxNzI4ZTIxZTA3Yzc0MDE2Nzk0MGY5YjdkOWIxYjE5NzljOGMzOGZmOGUxNjBhNWE3ZDFkOWVlODFmN2M2MmRlYzdmNDRiYzMxMTIwYmI2MjcxN2VkYmMwN2I3YjU4ZDVmYWY5ODJkZGEyZGY1NTM3ZTU4YTE=")))
            );


    }

    @Test
    public void testObtainSignature() {

          assertEquals(
            "2aa121d5ada947a6bf403c37e162ac3a605bf379c29ee208121c26dd4104a29f0b82c08bc07611715116ec614ce00564293d56915646dd0c195a2422d9cf7293" , 
            new String((signatureChecker.obtainPublicKey("MmFhMTIxZDVhZGE5NDdhNmJmNDAzYzM3ZTE2MmFjM2E2MDViZjM3OWMyOWVlMjA4MTIxYzI2ZGQ0MTA0YTI5ZjBiODJjMDhiYzA3NjExNzE1MTE2ZWM2MTRjZTAwNTY0MjkzZDU2OTE1NjQ2ZGQwYzE5NWEyNDIyZDljZjcyOTM=")))
            );

    }

    @Test
    public void testObtainWallet() {

           assertEquals(
            "2aa121d5ada947a6bf403c37e162ac3a605bf379c29ee208121c26dd4104a29f0b82c08b" , 
            new String((signatureChecker.obtainPublicKey("MmFhMTIxZDVhZGE5NDdhNmJmNDAzYzM3ZTE2MmFjM2E2MDViZjM3OWMyOWVlMjA4MTIxYzI2ZGQ0MTA0YTI5ZjBiODJjMDhi")))
            );

    }

    @Test
    public void testVerifySignature() {

    }
}
