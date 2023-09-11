package gyber.websocket.security.beta;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gyber.websocket.exceptions.BetaTestKeyException;
import gyber.websocket.models.repo.BetaTestKeyRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BetaTestKeyManagerTest {


    @Autowired
    private BetaTestKeyManager betaTestKeyManager;

    

    @Test
    public void testExistTsarKey(){
        assertNotNull(betaTestKeyManager.getTSAR_KEY());

    }


    // testGenerateMoreKeys

   @Test
   public void testGenerateMoreKeysInNormalScript() throws BetaTestKeyException {
      
        BetaTestKey[]arrKeys = this.betaTestKeyManager.testGenerateMoreKeys(30, this.betaTestKeyManager.getTSAR_KEY());
        assertTrue("Arr length not 30", (arrKeys.length == 30));


    }

    @Test(expected = BetaTestKeyException.class)
    public void testGenerateMoreKeysInIncorrectTsarKey() throws BetaTestKeyException{
        BetaTestKey[]arrKeys = this.betaTestKeyManager.testGenerateMoreKeys(30, "Incorrect tsar key");
         
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateMoreKeysInIncorrectNumber() throws BetaTestKeyException{
        BetaTestKey[]arrKeys = this.betaTestKeyManager.testGenerateMoreKeys(-30, "Incorrect tsar key");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateMoreKeysInEmptyString() throws BetaTestKeyException{
        BetaTestKey[]arrKeys = this.betaTestKeyManager.testGenerateMoreKeys(30, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateMoreKeysInNullString() throws BetaTestKeyException{
        BetaTestKey[]arrKeys = this.betaTestKeyManager.testGenerateMoreKeys(30, null);
    }


    
}
