package gyber.websocket.security.authenticate;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RTService.class)
public class RTServiceTest {


    @Autowired
    private RTService rtService;


    @Test
    public void testRTisNotNull(){
        String rtToken = this.rtService.createToken();
        assertNotNull("Test not passed. refresh token is null", rtToken);
    }

    @Test
    public void testRTisValid(){
        this.rtService.validateToken("VWdBMmJSWU5Uc3l0Q1ZGMlNvTkpwS08zMWc2Y0V5R3BNb2IxdGdXNG1CSWk0UlY3MlBFR1pCNURMSDVETkpJQjNpQXVKdzRsNkN5ZHp1NFNSdGVnekU3Y0NDalpxclR1RmRmcUNnbHVzU0huRWVKSTVXVDVzWUN4TWFRREdhUnFfMjAyMy0wOC0yNlQyMzowMDo1NS4xMjU3NjgxMjFfMTY5MzI1Mjg1NTEyNw==");

    }
    
}
