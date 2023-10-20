package gyber.sapphire.database.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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


 

    @Test
    void testFindById() {
        
    }


    @Test
    void testFindByIdWithInvalidId(){

    }

    @Test
    void testFindByUserName() {

        // assertNotNull(
        //         (this.userRepository
        //                 .findByUserName(
        //                         (saveAndGetAttribute(User::getUserName))))

        // );

    }

    @Test
    void testFindByUserNameWithUsernameIsNull(){

    }

    @Test
    void testFindByUserNameWithUsernameNotExist(){

    }

    @Test
    void testFindByUserNameWithNullUsername(){

    }

    @Test
    void testFindByUserNameWithEmptyUsername(){

    }


    // UPDATE
    @Test
    void testUpdateHashUserFile(){}


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
