package gyber.websocket.security.authenticate.signature;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.security.Signature;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

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

        try {
            String sig = "0x5942c9b4572c9342a5294223ed71cc0c6b6d87b50b8c487545535aa6a1fe380f79f36db9475d4255e99c27461213d8b6ade22819ab227a4a6607a102aaf7a6cb1b";
            String message = "\u0019Ethereum Signed Message:\n" + "Hello World".length() + "Hello World";
            String address = "0x28161A5F4A32C67fFb17e9bA722ddB8B342A970c";

            byte[] signatureBytes = Numeric.hexStringToByteArray(sig);

            byte[] r = new byte[32];
            byte[] s = new byte[32];
            byte v = signatureBytes[64];

            System.arraycopy(signatureBytes, 0, r, 0, 32);
            System.arraycopy(signatureBytes, 32, s, 0, 32);

            Sign.SignatureData signatureData = new Sign.SignatureData(v, r, s);

            BigInteger pubKey = Sign.signedMessageToKey(message.getBytes(), signatureData);

            String computedAddress = "0x" + Keys.getAddress(pubKey);
            if (computedAddress.equalsIgnoreCase(address)) {
                System.out.println("Signature verified!");
            } else {
                System.out.println("Invalid signature!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }   
        

    }
}
