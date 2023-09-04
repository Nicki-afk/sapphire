package gyber.websocket.security.authenticate.tokenManagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.ArgumentMatchers.refEq;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gyber.websocket.models.NetStatus;
import gyber.websocket.models.User;

/*
 * TODO : Усложнить тест. Добавить метод который генерирует множество параметров User
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TokenLocalStorageManager.class , JwtService.class , RTService.class})
public class TokenLocalStorageManagerTest {


    @Autowired
    private TokenLocalStorageManager tokenLocalStorageManagerTest;




    
    @Test
    public void testAddTokenPairForUser() {
        User user = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        assertNotNull(" Test not passed. Return object is null ", this.tokenLocalStorageManagerTest.addTokenPairForUser(user));
        assertTrue("Test not passed. Map size is 0", this.tokenLocalStorageManagerTest.getUserAndHisTokensPair().size() > 0);

    }

    @Test(expected = NullPointerException.class)
    public void testForNullAddTokenPairForObject(){
        this.tokenLocalStorageManagerTest.addTokenPairForUser(null);
    }

    @Test
    public void testExistTokenPair() {
        User user = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(user);
        assertTrue("Test not passed. Token pair not exist" , this.tokenLocalStorageManagerTest.existTokenPair(tokenPairObject));

    }


    /* isRefreshTokenBelongsThisUser()  */

    @Test
    public void testIsRefreshTokenBelongsThisUserByTue() {
        User user = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(user);
        assertTrue("Test not passed. Is token ...", this.tokenLocalStorageManagerTest.isRefreshTokenBelongsThisUser(user, tokenPairObject.getRefreshToken()));

    }

    @Test
    public void testIsRefreshTokenBelongsThisUserByFalse(){
        User user = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        assertFalse("Test not passed. Is token ...", this.tokenLocalStorageManagerTest.isRefreshTokenBelongsThisUser(user, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w"));
    }

    @Test(expected = NullPointerException.class)
    public void testIsRefreshTokenBelongsThisUserByNull(){
       this.tokenLocalStorageManagerTest.isRefreshTokenBelongsThisUser(2L, null);
        
    }

    @Test
    public void testIsRefreshTokenBelongsThisUserInUserIdByTrue(){
        User user = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(user);
        boolean result = this.tokenLocalStorageManagerTest.isRefreshTokenBelongsThisUser(2L, tokenPairObject.getRefreshToken());
        assertTrue(result);
    }

    @Test
    public void testIsRefreshTokenBelongsThisUserInUserIdByFalse(){
        User user = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(user);
        boolean result = this.tokenLocalStorageManagerTest.isRefreshTokenBelongsThisUser(4L, tokenPairObject.getRefreshToken());
        assertFalse(result);
    }





    @Test
    public void testGetUserByRefresh(){
        User user = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(user);
        User returnedUser = this.tokenLocalStorageManagerTest.getUserByRefresh(tokenPairObject.getRefreshToken());
        assertNotNull(returnedUser);
        assertEquals(user, returnedUser);
        
        
    }

    @Test
    public void testGetUserByJwt(){
        User putUser = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(putUser);
        User returnUser = this.tokenLocalStorageManagerTest.getUserByJwt(tokenPairObject.getJwtToken());
        assertNotNull(returnUser);
        assertEquals(putUser , returnUser);


    }


    @Test
    public void testDeleteTokenPairByUser(){
        User putUser = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(putUser);
        this.tokenLocalStorageManagerTest.deleteTokenPairUser(putUser);
        TokenPairObject nullTokenPairObject = this.tokenLocalStorageManagerTest.getTokenPairInUser(putUser);
        assertNull(nullTokenPairObject);

    }

    @Test
    public void testDeleteTokenPairByTokenPair(){
        User putUser = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(putUser);
        this.tokenLocalStorageManagerTest.deleteTokenPairUser(tokenPairObject);
        TokenPairObject nullTokenPairObject = this.tokenLocalStorageManagerTest.getTokenPairInUser(putUser);
        assertNull(nullTokenPairObject);
    }



    @Test
    public void testUpdateTokenPairUser() {
        User user = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(user);
        String oldRefresh = tokenPairObject.getRefreshToken();
        this.tokenLocalStorageManagerTest.updateTokenPairUser(user);
        String newRefresh = this.tokenLocalStorageManagerTest.getUserAndHisTokensPair().get(user).getRefreshToken();

        assertFalse("Test not passed. User old refresh is deleted", this.tokenLocalStorageManagerTest.exisistRefresh(oldRefresh));
        assertTrue("Test not passed. User new refresh is exists" , this.tokenLocalStorageManagerTest.exisistRefresh(newRefresh));


    }


  
}


