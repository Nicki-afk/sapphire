package gyber.websocket.security.authenticate.signature;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:test-source.properties")
public class RESTSignatureCheckerTest {


    @Autowired
    private MockMvc mvc;


    @Test
    public void testAuthenticateMethodInNullWalletArgument(){

        

    }






    
}
