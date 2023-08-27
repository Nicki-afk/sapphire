package gyber.websocket.security.authenticate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gyber.websocket.models.UserCustomDetails;
import gyber.websocket.security.authenticate.JwtService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JwtService.class)
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;



    @Test // Проверка на валидные значения 
    public void testJwtTokenWithValidVars(){

        UserCustomDetails userIPFSDetails = new UserCustomDetails(2L, "Nick", "JzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwibGFzdG5hbWUiOiJEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9)");
        String token = this.jwtService.createToken(userIPFSDetails);
        assertNotNull("Test no passed token is  null", token);


    }


    @Test(expected = NullPointerException.class) // Проверка на  null
    public void testJwtInNullParameter(){
        this.jwtService.createToken(null);
        System.out.println("Test passed . token can't be created becouse obect  null");
    
    }

    @Test(expected = NullPointerException.class)
    public void testJwtANameNullParameter(){
        this.jwtService.createToken(new UserCustomDetails(2L, null, "JzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwibGFzdG5hbWUiOiJEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9"));
        System.out.println("Test passed . Token can't be created becouse name is null");
       

    }

    @Test(expected = NullPointerException.class)
    public void testJwtANameIsEmptyParameter(){
        this.jwtService.createToken(new UserCustomDetails(2L, "", "JzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwibGFzdG5hbWUiOiJEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9"));
        System.out.println("Test passed . Token can't be created becouse name is empty");
    }

    @Test(expected = NullPointerException.class)
    public void testCryptoWalletAddresIsNullParameter(){
        this.jwtService.createToken(new UserCustomDetails(2L, "@ni_cko", null));
        System.out.println("Test passed. Token can't be created becouse cryptowallet aaddress is null");
    }


    @Test(expected = NullPointerException.class)
    public void testJwtCryptoWalletAddressIsEmpty(){
        this.jwtService.createToken(new UserCustomDetails(2L, "@nic_ko", ""));
        System.out.println("Test passed. Token can't be created becouse token field is null");
    }




    @Test // Проверка токена с валидным значением времени
    public void testJwtTokenIsValidToTime(){
        try{

            this.jwtService.setExpirationDate(60);
            String tokeString = this.jwtService.createToken(new UserCustomDetails(2L, "@nic_ko", "JzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwibGFzdG5hbWUiOiJEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9"));

            while(true){
                Thread.sleep(30000);
                break;

            }

            assertTrue("Token is not valid", this.jwtService.validateToken(tokeString));


        }catch(Exception e){
            e.printStackTrace();

        }

    }

    @Test // Проверка на токен с невалидным значением времени 
    public void testJwtTokenIsInvalidToTime(){
        try{

            this.jwtService.setExpirationDate(30);
            String tokeString = this.jwtService.createToken(new UserCustomDetails(2L, "@nic_ko", "JzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwibGFzdG5hbWUiOiJEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9"));

            while(true){
                Thread.sleep(40000); // 40 sec
                break;

            }

            assertFalse("Token is  valid", this.jwtService.validateToken(tokeString));


        }catch(Exception e){
            e.printStackTrace();

        }

    }


    @Test(expected = JwtException.class) // Проверка подписи сервера 
    public void testJwtTokenForInvalidSignature(){
        String tokenInTrueSignature = this.jwtService.createToken(new UserCustomDetails(2L, "@nic_ko", "JzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwibGFzdG5hbWUiOiJEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9"));
        this.jwtService.setSigningKey("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwibGFzdG5hbWUiOiJEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9IsmKOjHyHYqS4cAmljpBb0fCGuCAs5xN5qy8elpts");
        String username = this.jwtService.getUserNameInToken(tokenInTrueSignature);

    }



    
}
