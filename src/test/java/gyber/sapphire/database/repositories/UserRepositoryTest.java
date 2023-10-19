package gyber.sapphire.database.repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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



    private User generateRandomUser() {
        return new User(
            "@" + new RandomString(5).nextString(),
            "0x" + new RandomString(16).nextString(),
            new RandomString(128).nextString(),
            NetStatus.ONLINE,
            new RandomString(200).nextString().toUpperCase()
        );
    }



    @BeforeEach
    public void moreUsers() {

        for (int x = 0; x < 2; x++) {


            this.userRepository
            .save(generateRandomUser());

            System.out.println("USER SAVE ");

        }

        System.out.println(
                "\n\n\n\n Users Saved  : "
                        +
                        (this.userRepository.count())
                        +
                        "\n\n\n\n");

    }

    private String saveAndGetAttribute(Function<User, String> function) {
        User localUser = userRepository.save(generateRandomUser());
        return function.apply(localUser);
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

    //@Test
    @ParameterizedTest
    @MethodSource("getMoreUserParameters")
    public void testSaveUser(User user){
 
        User userToSaveTest = this.userRepository.save(user);
        assertNotNull( userToSaveTest);

        User userToGetTest = this.userRepository.findByUserName( (userToSaveTest.getUserName()) ).get();
        assertNotNull(userToGetTest); 


        assertTrue(userToSaveTest.equals(userToGetTest));
        
    }




    // READ
    @Test
    @Disabled("The test is disabled because testing of the BetaTestKeyRepository  is required")
    void testFindByBetaTestKey() {

    }

    @Test
    void testFindByCryptoWalletAddress() {

        assertNotNull(
                (this.userRepository
                        .findByCryptoWalletAddress((saveAndGetAttribute(User::getCryptoWalletAddress)))));
                        
    }




    @Test
    void testFindByCryptoWalletAddressWithNullAddress(){

        Exception exception = assertThrows(Exception.class, () -> this.userRepository.findByCryptoWalletAddress(null).orElse(new User()));
        assertNotNull(exception);

    

       
    }

    @Test
    void testFindByCryptoWalletAddressWithAddressNotExist(){

        assertFalse( (this.userRepository.findByCryptoWalletAddress("OIUcnwiuhcuih98h9hwf").isPresent()));

    }

    @Test
    void testFindByCryptoWalletAddressWithEmptyString(){

        assertFalse( (this.userRepository.findByCryptoWalletAddress("").isPresent()));

    }



    @Test
    void testFindByHashUserFile() {
        
        assertNotNull(
         (this.userRepository.findByHashUserFile((saveAndGetAttribute(User::getHashUserFile))))    
        );

    }


    @Test
    void testFindByHashUserFileWithNull(){

    }


    @Test
    void testFindByHashUserFileWithEmptyString(){

    }

    @Test
    void testFindByHashUserFileWithHashFileNotExist(){

    }

    @Test
    void testFindById() {
        assertNotNull((userRepository.findById(1L).get()));
    }


    @Test
    void testFindByIdWithInvalidId(){

    }

    @Test
    void testFindByUserName() {

        assertNotNull(
                (this.userRepository
                        .findByUserName(
                                (saveAndGetAttribute(User::getUserName))))

        );

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
