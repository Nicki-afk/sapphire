package gyber.websocket.security.authenticate.tokenManagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TokenLocalStorageManager.class , JwtService.class , RTService.class})
public class TokenLocalStorageManagerTest {


    @Autowired
    private TokenLocalStorageManager tokenLocalStorageManagerTest;


    public Stream<User> theUserMethodSource(){
        return
            Stream.of(
                new User(1L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.DEPARTED, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w")  , 
                new User(2L, "@Zip_o", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w")  , 
                new User(3L, "@Xios", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.WAS_RECENTLY, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w")  , 
                new User(4L, "@Nobis", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.DEPARTED, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w")  
            );
    }


    public static Stream<User>get100ObjectUserForThreadingTest(){

        User[]moreUsers = new User[2];
        for(int x = 0; x < moreUsers.length; x++ ){
            moreUsers[x] = new User(
            (long) x,
            "@".concat(new RandomString(6).nextString()),
            new RandomString(128).nextString(),
            new RandomString(128).nextString(),
            NetStatus.ONLINE,
            new RandomString(200).nextString()
            );

        }

        return Arrays.stream(moreUsers);
    }


    
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
        this.tokenLocalStorageManagerTest.isRefreshTokenBelongsThisUser(null, "null");
    }


    @ParameterizedTest
    @MethodSource("theUserMethodSource")
    public void generalTest(User user){
        TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(user);
        assertNotNull("Test not passed. Token pair object is null ", tokenPairObject);
        assertTrue("Test not passed. Token pair not exist", this.tokenLocalStorageManagerTest.existTokenPair(tokenPairObject));
        assertEquals("Test not passed. User not found by refresh", user , this.tokenLocalStorageManagerTest.getUserByRefresh(tokenPairObject.getRefreshToken()));
        assertEquals("Test not passed. User not found by jwt", user , this.tokenLocalStorageManagerTest.getUserByJwt(tokenPairObject.getJwtToken()));
        assertTrue("Test not passed. Refresh token exist", this.tokenLocalStorageManagerTest.exisistRefresh(tokenPairObject.getRefreshToken()));

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


    @ParameterizedTest
    @MethodSource("get100ObjectUserForThreadingTest")
    public void generalThreadTest(User user){
       ExecutorService executorService = Executors.newFixedThreadPool(20);
       CountDownLatch countDownLatch = new CountDownLatch(20);
       //TokenPairObject tokenPairObject = new TokenPairObject();

       // simple write
       for(int x = 0; x < 20; x++){
            executorService.submit(() -> {
                TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(user);
                assertNotNull("Token pair object is null", tokenPairObject);
                countDownLatch.countDown();
            });

       }

       // simple read
       for(int x = 0; x < 20; x++){
            executorService.submit(() -> {
                TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.getUserAndHisTokensPair().get(user);
                assertNotNull(tokenPairObject);
                countDownLatch.countDown();
            });
       }


       try {

            countDownLatch.await();

       } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
       }

       assertTrue("Test not passed. Map.lenght < 100. Map length == ".concat(this.tokenLocalStorageManagerTest.getUserAndHisTokensPair().size() + ""), (this.tokenLocalStorageManagerTest.getUserAndHisTokensPair().size() == 100));


       


    }
}


