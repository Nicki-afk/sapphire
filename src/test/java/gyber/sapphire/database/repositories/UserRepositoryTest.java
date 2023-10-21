package gyber.sapphire.database.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import gyber.sapphire.beta.BetaTestKey;
import gyber.sapphire.profile.NetStatus;
import gyber.sapphire.profile.User;

@DataJpaTest
@TestPropertySource("classpath:test-source.properties")
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private List<String> usersHash = new ArrayList<>() , usernames = new ArrayList<>();



    private User generateOnlyOneUser() {
        return new User(
            "@" + new RandomString(5).nextString(),
            "0x" + new RandomString(16).nextString(),
            new RandomString(128).nextString(),
            NetStatus.ONLINE,
            new RandomString(200).nextString().toUpperCase()
        );
    }






    public static Stream<Arguments> getMoreUserParameters(){
        return Stream.of(
            Arguments.of(
                (
                new User(
                "@" + new RandomString(5).nextString(),
                "0x" + new RandomString(16).nextString(),
                new RandomString(128).nextString(),
                NetStatus.ONLINE,
                new RandomString(200).nextString().toUpperCase()

                 )
                )
            ) , 

            Arguments.of(
                (
                    new User(
                    "@" + new RandomString(5).nextString(),
                    "0x" + new RandomString(16).nextString(),
                    new RandomString(128).nextString(),
                    NetStatus.ONLINE,
                    new RandomString(200).nextString().toUpperCase()

                    )
                )
            ) , 

            Arguments.of(
                (
                    new User(
                        "@" + new RandomString(5).nextString(),
                        "0x" + new RandomString(16).nextString(),
                        new RandomString(128).nextString(),
                        NetStatus.ONLINE,
                        new RandomString(200).nextString().toUpperCase()

                    )
                )
            ) 

        );
    }


    // SAVE

    @ParameterizedTest
    @MethodSource("getMoreUserParameters")
    void testSaveUser(User user){
 
        User userToSaveTest = this.userRepository.save(user);
        assertNotNull( userToSaveTest);

        User userToGetTest = this.userRepository.findByUserName( (userToSaveTest.getUserName()) ).get();
        assertNotNull(userToGetTest); 


        assertTrue((userToSaveTest.equals(userToGetTest)));
        assertTrue((userToSaveTest.getUserName().equals(userToGetTest.getUserName()))); // additional check
        
    }




    // READ
    @Test
    @Disabled("The test is disabled because testing of the BetaTestKeyRepository  is required")
    void testFindByBetaTestKey() {

    }

    @ParameterizedTest
    @MethodSource("getMoreUserParameters")
    void testFindByCryptoWalletAddress(User user) {

        User userToSaveTest = this.userRepository.save(user);
        assertNotNull( userToSaveTest);

        String crypto = userToSaveTest.getCryptoWalletAddress();
        assertNotNull(crypto);

        User userToGetTest = this.userRepository.findByCryptoWalletAddress( crypto ).get();
        assertNotNull(userToGetTest); 


        assertTrue((userToSaveTest.equals(userToGetTest)));
        assertTrue(
            (userToSaveTest.getCryptoWalletAddress().equals(userToGetTest.getCryptoWalletAddress()))
        );


                        
    }




    @ParameterizedTest
    @NullAndEmptySource
    void testFindByCryptoWalletAddressWithNullAddressOrEmptyAddress(String wallet){

        User userToSave = ( this.userRepository.save( generateOnlyOneUser() ) );
        assertNotNull("User is null", userToSave);
        assertNotNull("Saved User entity have a wallet address null" ,  (userToSave.getCryptoWalletAddress()) );

        User userToGet = ( (this.userRepository.findByCryptoWalletAddress(wallet)).orElse(null));
        assertNull(userToGet);

    }


  



    @ParameterizedTest 
    @MethodSource("getMoreUserParameters")
    void testFindByHashUserFile(User user) {
        
        User userToSave = ( this.userRepository.save( user ) );
        assertNotNull("User is null", userToSave);
        assertNotNull("Saved User entity have a hash file  null" ,  (userToSave.getHashUserFile()) );

        User userToGet = ( (this.userRepository.findByHashUserFile( (userToSave.getHashUserFile()))).orElse(null));
        assertNotNull(userToGet);

        assertTrue((userToSave.equals(userToGet)));
        assertEquals(userToSave.getHashUserFile() , userToGet.getHashUserFile());
    

    }


    @ParameterizedTest
    @NullAndEmptySource
    void testFindByHashUserFileWithNullOrEmptyInput(String hashUserFile){

        User userToSave = ( this.userRepository.save( generateOnlyOneUser() ) );
        assertNotNull("User is null", userToSave);
        assertNotNull("Save User hash file is null " , (userToSave.getHashUserFile()));
     
        User userToGet = ( (this.userRepository.findByHashUserFile( hashUserFile)).orElse(null));
        assertNull(userToGet);


    }


 

    @ParameterizedTest
    @MethodSource("getMoreUserParameters")
    void testFindById(User user) {

        User userToSave = ( this.userRepository.save( user ) );
        assertNotNull("User is null", userToSave);
        assertNotNull("Saved User entity have a  invalid id" ,  (userToSave.getId()) );

        User userToGet = ( (this.userRepository.findById( (userToSave.getId()))).orElse(null));
        assertNotNull(userToGet);


        
    }


    @ParameterizedTest
    @ValueSource(longs = {-1 , 0 , -0 , -129012})
    void testFindByIdWithInvalidId(Long invalidId){

        User userToSave = ( this.userRepository.save( (generateOnlyOneUser()) ) );
        assertNotNull(userToSave);

        User userToGet = this.userRepository.findById(invalidId).orElse(null);
        assertNull(userToGet);
        

    }

    @ParameterizedTest
    @MethodSource("getMoreUserParameters")
    void testFindByUserName(User user) {

        User userToSave = ( this.userRepository.save(user) );
        assertNotNull(userToSave);
        assertNotNull((userToSave.getUserName()));

        User userToGet = this.userRepository.findByUserName((userToSave.getUserName())).orElse(null);
        assertNotNull(userToGet);
        assertTrue( (userToSave.equals(userToGet)) );
        assertEquals( (userToSave.getUserName())  , (userToGet.getUserName()) ); 


    }


    @ParameterizedTest
    @NullAndEmptySource
    void testFindByUserNameWithNullOrEmptyInput(String userName){
        User userToSave = this.userRepository.save( (generateOnlyOneUser()) );
        assertNotNull(userToSave);
        assertFalse( (userToSave.getUserName().isEmpty()) );

        User userToGet = this.userRepository.findByUserName(userName).orElse(null);
        assertNull(userToGet);


    }


    // UPDATE
    // @ParameterizedTest
    // @MethodSource("getMoreUserParameters")
    @Test
    void testUpdateHashUserFile(){
        User userBeforeUpdate = this.userRepository.saveAndFlush( (generateOnlyOneUser()) );
        assertNotNull( userBeforeUpdate);


        Long userID = (userBeforeUpdate.getId());
        this.userRepository.updateHashUserFile( userID , "sgljwofjwofiwoifhwoifwifhwoifhwoifh");
        this.userRepository.flush();
        
    

        User userAfterUpdate = this.userRepository.findById(userID).orElse(null);

        assertNotNull(userAfterUpdate);
        assertNotEquals(userBeforeUpdate , userAfterUpdate);
        assertNotEquals( (userBeforeUpdate.getHashUserFile()) , (userAfterUpdate.getHashUserFile()) );

    }




    @Test
    void testUpdateUsername(){}


    @Test
    void testUpdateCryptoWalletAddress(){}


    @Test
    void testUpdateNetStatus(){}




    // DELETE
    @Test
    void testDeleteById(){}

    @Test
    void testDeleteByUserName(){}

    @Test
    void testDeleteByCryptoWalletAddress(){}







}
