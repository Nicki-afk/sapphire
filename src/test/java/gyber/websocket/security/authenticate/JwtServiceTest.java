package gyber.websocket.security.authenticate;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gyber.websocket.models.UserIPFSDetails;
import gyber.websocket.security.authenticate.JwtService;
import io.jsonwebtoken.Jwts;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JwtService.class)
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;


    
}
