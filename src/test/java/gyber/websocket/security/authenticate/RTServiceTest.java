package gyber.websocket.security.authenticate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.el.stream.Stream;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gyber.websocket.security.authenticate.tokenManagement.RTService;

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


    @Test(expected = NullPointerException.class)
    public void testRtIsNull(){
        this.rtService.validateToken(null);

    }


    @Test
    public void testRTWithOverdueDate(){
        this.rtService.setExpirationDate(30);

        String token = this.rtService.createToken();

        try{
            while(true){
                Thread.sleep(40000);
                break;
            }
            
            assertFalse("token", this.rtService.validateToken(token));

        }catch(Exception e){
            e.printStackTrace();
        }

    }



    @Test
    public void testRTWithVaildDate(){
        this.rtService.setExpirationDate(20);
        String token = this.rtService.createToken();

        try{
            while(true){
                Thread.sleep(19000);
                break;

            }

            assertTrue("Token validation test is false , but must be true. Token is valid ", this.rtService.validateToken(token));


        }catch(Exception e){
            e.printStackTrace();

        }
    }


    @Test
    public void testRTisValid(){
        this.rtService.validateToken("VWdBMmJSWU5Uc3l0Q1ZGMlNvTkpwS08zMWc2Y0V5R3BNb2IxdGdXNG1CSWk0UlY3MlBFR1pCNURMSDVETkpJQjNpQXVKdzRsNkN5ZHp1NFNSdGVnekU3Y0NDalpxclR1RmRmcUNnbHVzU0huRWVKSTVXVDVzWUN4TWFRREdhUnFfMjAyMy0wOC0yNlQyMzowMDo1NS4xMjU3NjgxMjFfMTY5MzI1Mjg1NTEyNw==");

    }
    
}
