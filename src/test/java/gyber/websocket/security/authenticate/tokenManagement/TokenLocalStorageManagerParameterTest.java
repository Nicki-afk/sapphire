package gyber.websocket.security.authenticate.tokenManagement;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import gyber.sapphire.authentication.tokens.JwtService;
import gyber.sapphire.authentication.tokens.RTService;
import gyber.sapphire.authentication.tokens.TokenLocalStorageManager;
import gyber.sapphire.authentication.tokens.TokenPairObject;
import gyber.sapphire.errors.TokenLocalStorageException;
import gyber.sapphire.profile.NetStatus;
import gyber.sapphire.profile.User;

@Disabled
@SpringBootTest(classes =  {TokenLocalStorageManager.class , JwtService.class , RTService.class})
public class TokenLocalStorageManagerParameterTest {


    @Autowired
    private TokenLocalStorageManager tokenLocalStorageManagerTest;

    



    public static User[] get100ObjectUserForThreadingTest(){

        User[]moreUsers = new User[20];
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

        return moreUsers;
    }

    public static Stream<User> theUserMethodSource(){
        return
            Stream.of(
                new User(1L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.DEPARTED, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w")  , 
                new User(2L, "@Zip_o", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w")  , 
                new User(3L, "@Xios", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.WAS_RECENTLY, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w")  , 
                new User(4L, "@Nobis", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.DEPARTED, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w")  
            );
    }


    @ParameterizedTest
    @MethodSource("theUserMethodSource")
    public void generalTest(User user) throws TokenLocalStorageException{
        TokenPairObject tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(user);
        assertNotNull(tokenPairObject, "Test not passed. Token pair object is null ");
        assertTrue(this.tokenLocalStorageManagerTest.existTokenPair(tokenPairObject), "Test not passed. Token pair not exist");
        assertEquals(this.tokenLocalStorageManagerTest.getUserByRefresh(tokenPairObject.getRefreshToken()) , user , "Test not passed. User not found by refresh");
        assertEquals(this.tokenLocalStorageManagerTest.getUserByJwt(tokenPairObject.getJwtToken()) , user , "Test not passed. User not found by jwt");
        assertTrue(this.tokenLocalStorageManagerTest.exisistRefresh(tokenPairObject.getRefreshToken()), "Test not passed. Refresh token exist");


    }



       /*
            * Добавлен sleep для коректного выполнения теста. Нужна небольшая пауза 
            * что бы гаранитированно были прочитаны валидные и свежие значения из Map
            * 
            * TODO : В будущем обязательно надо редактировать тест , и проверить его 
            *        в более правдоподобных условиях 
        */

    /*
     Тест на 2 операции ( чтение , запись )
    */    
    @RepeatedTest(30)
    @Timeout(value = 10)
    public void generalThreadTest(){
       ExecutorService executorService = Executors.newFixedThreadPool(20);
       CountDownLatch writeCountDownLatch = new CountDownLatch(20);

       this.tokenLocalStorageManagerTest.getUserAndHisTokensPair().clear();
    
    
            // simple write
            for(int x = 0; x < 20; x++){
                    executorService.submit(() -> {
                        User user = new User(
                                    0L,
                                    "@".concat(new RandomString(6).nextString()),
                                    new RandomString(128).nextString(),
                                    new RandomString(128).nextString(),
                                    NetStatus.ONLINE,
                                    new RandomString(200).nextString()
                                    );
                                   
                        TokenPairObject tokenPairObject = null;
                        try {
                            tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(user);
                        } catch (TokenLocalStorageException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    
                        assertNotNull(tokenPairObject, "Token pair object is null");
                        writeCountDownLatch.countDown();
                    });
                   

            }

            try {
             
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

         

            User[] users = this.tokenLocalStorageManagerTest.getUserAndHisTokensPair().keySet().toArray(new User[]{});

            // simple read
            for(User user : users){
                    executorService.submit(() -> {
                        TokenPairObject tokenPairObject = null;
                        try {
                            tokenPairObject = this.tokenLocalStorageManagerTest.getTokenPairInUser(user);
                        } catch (TokenLocalStorageException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        assertNotNull(tokenPairObject);
                       
                        writeCountDownLatch.countDown();
                    });
            }
        


       try {

            writeCountDownLatch.await();


       } catch (InterruptedException e) {
        
          e.printStackTrace();
       }

       executorService.shutdown();
       assertTrue((this.tokenLocalStorageManagerTest.getUserAndHisTokensPair().size() == 20) , "Test not passed. Map.lenght < 20. Map length = ".concat(this.tokenLocalStorageManagerTest.getUserAndHisTokensPair().size() + ""));
      
       

    }


    @RepeatedTest(20)
    @Timeout(10)
    @Test
    public void praallelTestForForOpertionsInTokenLocalStorage(){
       ExecutorService executorService = Executors.newFixedThreadPool(20);
       CountDownLatch writeCountDownLatch = new CountDownLatch(20);


        this.tokenLocalStorageManagerTest.getUserAndHisTokensPair().clear();
    
    
            // simple write
            for(int x = 0; x < 20; x++){
                    executorService.submit(() -> {
                        User user = new User(
                                    0L,
                                    "@".concat(new RandomString(6).nextString()),
                                    new RandomString(128).nextString(),
                                    new RandomString(128).nextString(),
                                    NetStatus.ONLINE,
                                    new RandomString(200).nextString()
                                    );
                                   
                        TokenPairObject tokenPairObject = null;
                        try {
                            tokenPairObject = this.tokenLocalStorageManagerTest.addTokenPairForUser(user);
                        } catch (TokenLocalStorageException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    
                        assertNotNull(tokenPairObject, "Token pair object is null");
                        writeCountDownLatch.countDown();
                    });
                   

            }

            try {
             
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

         

            User[] users = this.tokenLocalStorageManagerTest.getUserAndHisTokensPair().keySet().toArray(new User[]{});

            // simple read
            for(User user : users){
                    executorService.submit(() -> {
                        TokenPairObject tokenPairObject = null;
                        try {
                            tokenPairObject = this.tokenLocalStorageManagerTest.getTokenPairInUser(user);
                        } catch (TokenLocalStorageException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        assertNotNull(tokenPairObject);
                        writeCountDownLatch.countDown();
                    });
            }


            // simple update
            // for(User user : users){

            //        executorService.submit(() -> {
            //             TokenPairObject oldTokenPair = this.tokenLocalStorageManagerTest.getTokenPairInUser(user);
            //             assertNotNull(oldTokenPair);

            //             this.tokenLocalStorageManagerTest.updateTokenPairUser(user);

            //             TokenPairObject newTokenPair = this.tokenLocalStorageManagerTest.getTokenPairInUser(user);
            //             assertNotNull(newTokenPair);
            //             assertNotEquals(oldTokenPair , newTokenPair);

            //             User userByTokenPair = this.tokenLocalStorageManagerTest.getUserByRefresh(newTokenPair.getRefreshToken());
            //             assertNotNull(users);
            //             assertEquals(userByTokenPair , user);

            //             writeCountDownLatch.countDown();
            //         });

            // }


            // simple delete
            // ... 
        


       try {

            writeCountDownLatch.await();


       } catch (InterruptedException e) {
        
          e.printStackTrace();
       }


       

    }


   


    
    
}
