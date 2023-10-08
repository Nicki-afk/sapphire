package gyber.websocket.security.beta;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import gyber.sapphire.beta.BetaTestKey;
import gyber.sapphire.beta.BetaTestKeyManager;
import gyber.sapphire.database.repositories.BetaTestKeyRepository;
import gyber.sapphire.errors.BetaTestKeyException;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test-source.properties")
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

    @Test(expected = BetaTestKeyException.class)
    public void testGenerateMoreKeysInIncorrectNumber() throws BetaTestKeyException{
        BetaTestKey[]arrKeys = this.betaTestKeyManager.testGenerateMoreKeys(-30, this.betaTestKeyManager.getTSAR_KEY());
    }

    @Test(expected = BetaTestKeyException.class)
    public void testGenerateMoreKeysInEmptyString() throws BetaTestKeyException{
        BetaTestKey[]arrKeys = this.betaTestKeyManager.testGenerateMoreKeys(30, "");
    }

    @Test(expected = BetaTestKeyException.class)
    public void testGenerateMoreKeysInNullString() throws BetaTestKeyException{
        BetaTestKey[]arrKeys = this.betaTestKeyManager.testGenerateMoreKeys(30, null);
    }

    /*
     * Тест ниже проверяет не просрочены ли сгенерированные ключи. Тестовый ключ валиден 1 минуту 
     * тест выполняется 59 секунд , тестовые ключи должны быть валидны еще 1 секунду.
    */
    @Test
    public void testIsBetaKeyIsValidTestExpirationValid() {
    

        try{
            BetaTestKey[]arrKeys = this.betaTestKeyManager.testGenerateMoreKeys(30, this.betaTestKeyManager.getTSAR_KEY());
            this.betaTestKeyManager.saveMoreKeys(arrKeys);

            Thread.sleep(59000);

            int x = 0;
            while(x < arrKeys.length){

                String key = arrKeys[x].getKey();
                assertTrue("Key is not valid", this.betaTestKeyManager.isBetaKeyIsValid(key));
                x++;
            }

        }catch(Exception  e){
            e.printStackTrace();

        }


    }


     /*
     * Тест ниже проверяет не просрочены ли сгенерированные ключи. Тестовый ключ валиден 1 минуту 
     * тест выполняется 61 секунду , тестовые ключи уже не валидны 1 секунду и не могут быть использованы  
     */
    @Test
    public void testIsBetaKeyIsValidTestExpirationInvalid() {
    

        try{
            BetaTestKey[]arrKeys = this.betaTestKeyManager.testGenerateMoreKeys(30, this.betaTestKeyManager.getTSAR_KEY());
            this.betaTestKeyManager.saveMoreKeys(arrKeys);

            Thread.sleep(61000);

            int x = 0;
            while(x < arrKeys.length){

                String key = arrKeys[x].getKey();
                assertFalse("Key is valid but key should not valid", this.betaTestKeyManager.isBetaKeyIsValid(key));
                x++;
            }

        }catch(Exception  e){
            e.printStackTrace();

        }


    }


    
}
