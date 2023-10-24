package gyber.sapphire.database.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import gyber.sapphire.profile.NetStatus;
import gyber.sapphire.profile.User;

@DataJpaTest
@TestPropertySource("classpath:test-source.properties")
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private List<String> usersHash = new ArrayList<>(), usernames = new ArrayList<>();

    private User generateOnlyOneUser() {
        return new User(
                "@" + new RandomString(5).nextString(),
                "0x" + new RandomString(16).nextString(),
                new RandomString(128).nextString(),
                NetStatus.DEPARTED,
                new RandomString(200).nextString().toUpperCase());
    }

    public static Stream<Arguments> getMoreUserParameters() {
        return Stream.of(
                Arguments.of(
                        (new User(
                                "@" + new RandomString(5).nextString(),
                                "0x" + new RandomString(16).nextString(),
                                new RandomString(128).nextString(),
                                NetStatus.ONLINE,
                                new RandomString(200).nextString().toUpperCase()

                        ))),

                Arguments.of(
                        (new User(
                                "@" + new RandomString(5).nextString(),
                                "0x" + new RandomString(16).nextString(),
                                new RandomString(128).nextString(),
                                NetStatus.ONLINE,
                                new RandomString(200).nextString().toUpperCase()

                        ))),

                Arguments.of(
                        (new User(
                                "@" + new RandomString(5).nextString(),
                                "0x" + new RandomString(16).nextString(),
                                new RandomString(128).nextString(),
                                NetStatus.ONLINE,
                                new RandomString(200).nextString().toUpperCase()

                        )))

        );
    }

    // SAVE

    @ParameterizedTest
    @MethodSource("getMoreUserParameters")
    void testSaveUser(User user) {

        User userToSaveTest = this.userRepository.save(user);
        assertNotNull(userToSaveTest);

        User userToGetTest = this.userRepository.findByUserName((userToSaveTest.getUserName())).get();
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
        assertNotNull(userToSaveTest);

        String crypto = userToSaveTest.getCryptoWalletAddress();
        assertNotNull(crypto);

        User userToGetTest = this.userRepository.findByCryptoWalletAddress(crypto).get();
        assertNotNull(userToGetTest);

        assertTrue((userToSaveTest.equals(userToGetTest)));
        assertTrue(
                (userToSaveTest.getCryptoWalletAddress().equals(userToGetTest.getCryptoWalletAddress())));

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testFindByCryptoWalletAddressWithNullAddressOrEmptyAddress(String wallet) {

        User userToSave = (this.userRepository.save(generateOnlyOneUser()));
        assertNotNull("User is null", userToSave);
        assertNotNull("Saved User entity have a wallet address null", (userToSave.getCryptoWalletAddress()));

        User userToGet = ((this.userRepository.findByCryptoWalletAddress(wallet)).orElse(null));
        assertNull(userToGet);

    }

    @ParameterizedTest
    @MethodSource("getMoreUserParameters")
    void testFindByHashUserFile(User user) {

        User userToSave = (this.userRepository.save(user));
        assertNotNull("User is null", userToSave);
        assertNotNull("Saved User entity have a hash file  null", (userToSave.getHashUserFile()));

        User userToGet = ((this.userRepository.findByHashUserFile((userToSave.getHashUserFile()))).orElse(null));
        assertNotNull(userToGet);

        assertTrue((userToSave.equals(userToGet)));
        assertEquals(userToSave.getHashUserFile(), userToGet.getHashUserFile());

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testFindByHashUserFileWithNullOrEmptyInput(String hashUserFile) {

        User userToSave = (this.userRepository.save(generateOnlyOneUser()));
        assertNotNull("User is null", userToSave);
        assertNotNull("Save User hash file is null ", (userToSave.getHashUserFile()));

        User userToGet = ((this.userRepository.findByHashUserFile(hashUserFile)).orElse(null));
        assertNull(userToGet);

    }

    @ParameterizedTest
    @MethodSource("getMoreUserParameters")
    void testFindById(User user) {

        User userToSave = (this.userRepository.save(user));
        assertNotNull("User is null", userToSave);
        assertNotNull("Saved User entity have a  invalid id", (userToSave.getId()));

        User userToGet = ((this.userRepository.findById((userToSave.getId()))).orElse(null));
        assertNotNull(userToGet);

    }

    @ParameterizedTest
    @ValueSource(longs = { -1, 0, -0, -129012 })
    void testFindByIdWithInvalidId(Long invalidId) {

        User userToSave = (this.userRepository.save((generateOnlyOneUser())));
        assertNotNull(userToSave);

        User userToGet = this.userRepository.findById(invalidId).orElse(null);
        assertNull(userToGet);

    }

    @ParameterizedTest
    @MethodSource("getMoreUserParameters")
    void testFindByUserName(User user) {

        User userToSave = (this.userRepository.save(user));
        assertNotNull(userToSave);
        assertNotNull((userToSave.getUserName()));

        User userToGet = this.userRepository.findByUserName((userToSave.getUserName())).orElse(null);
        assertNotNull(userToGet);
        assertTrue((userToSave.equals(userToGet)));
        assertEquals((userToSave.getUserName()), (userToGet.getUserName()));

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testFindByUserNameWithNullOrEmptyInput(String userName) {
        User userToSave = this.userRepository.save((generateOnlyOneUser()));
        assertNotNull(userToSave);
        assertFalse((userToSave.getUserName().isEmpty()));

        User userToGet = this.userRepository.findByUserName(userName).orElse(null);
        assertNull(userToGet);

    }

    // UPDATE
    @ParameterizedTest
    @MethodSource("getMoreUserParameters")
    void testUpdateHashUserFile(User user) {
        User userBeforeUpdate = this.userRepository.saveAndFlush(user);
        assertNotNull(userBeforeUpdate);

        Long userID = (userBeforeUpdate.getId());
        this.userRepository.updateHashUserFile(userID, "sgljwofjwofiwoifhwoifwifhwoifhwoifh");
        this.userRepository.flush();

        User userAfterUpdate = this.userRepository.findById(userID).orElse(null);

        assertNotNull(userAfterUpdate);
        assertNotEquals(userBeforeUpdate, userAfterUpdate);
        assertNotEquals((userBeforeUpdate.getHashUserFile()), (userAfterUpdate.getHashUserFile()));

    }

    /*
     * The repository allows you to save values that are null or empty
     */
    @ParameterizedTest
    @NullAndEmptySource
    void testUpdateHashUserFileWithNullOrEmptyInput(String hashFile) {

        User userBeforeUpdate = this.userRepository.saveAndFlush((generateOnlyOneUser()));
        assertNotNull(userBeforeUpdate);

        Long userID = (userBeforeUpdate.getId());
        this.userRepository.updateHashUserFile(userID, hashFile);
        this.userRepository.flush();

        User userAfterUpdate = this.userRepository.findById(userID).orElse(null);
        assertNotNull(userAfterUpdate);

        assertTrue((userAfterUpdate.getHashUserFile() == null || userAfterUpdate.getHashUserFile().isEmpty()));

    }

    @ParameterizedTest
    @MethodSource("getMoreUserParameters")
    void testUpdateUsername(User user) {

        User userBeforeUpdate = this.userRepository.saveAndFlush(user);
        assertNotNull(userBeforeUpdate);

        Long userID = (userBeforeUpdate.getId());
        this.userRepository.updateUserName(userID, "@simple");
        this.userRepository.flush();

        User userAfterUpdate = this.userRepository.findById(userID).orElse(null);

        assertNotNull(userAfterUpdate);
        assertNotEquals(userBeforeUpdate, userAfterUpdate);
        assertNotEquals((userBeforeUpdate.getUserName()), (userAfterUpdate.getUserName()));

    }

    @Test
    @Rollback(true)
    void testUpdateUsernameWhichAlreadyTaken() {
        User userOne = generateOnlyOneUser();
        User userTwo = generateOnlyOneUser();

        assertNotNull((this.userRepository.saveAndFlush(userOne)));
        assertNotNull((this.userRepository.saveAndFlush(userTwo)));

        Exception e = assertThrows(DataIntegrityViolationException.class,
                () -> this.userRepository.updateUserName((userTwo.getId()), (userOne.getUserName())));

        assertNotNull(e);
        assertInstanceOf(DataIntegrityViolationException.class, e);

    }

    public static Stream<Arguments> getUserObjectsAndAddressesWhichNeedUpdate() {
        return Stream
                .of(
                        Arguments.of(
                                (new User(
                                        "@" + new RandomString(5).nextString(),
                                        "0x" + new RandomString(16).nextString(),
                                        new RandomString(128).nextString(),
                                        NetStatus.ONLINE,
                                        new RandomString(200).nextString().toUpperCase()

                                )), ("0x" + new RandomString(16).nextString())

                        ),

                        Arguments.of(
                                (new User(
                                        "@" + new RandomString(5).nextString(),
                                        "0x" + new RandomString(16).nextString(),
                                        new RandomString(128).nextString(),
                                        NetStatus.ONLINE,
                                        new RandomString(200).nextString().toUpperCase()

                                )), ("0x" + new RandomString(16).nextString())

                        ),

                        Arguments.of(
                                (new User(
                                        "@" + new RandomString(5).nextString(),
                                        "0x" + new RandomString(16).nextString(),
                                        new RandomString(128).nextString(),
                                        NetStatus.ONLINE,
                                        new RandomString(200).nextString().toUpperCase()

                                )), ("0x" + new RandomString(16).nextString())

                        )

                );
    }

    @ParameterizedTest
    @MethodSource("getUserObjectsAndAddressesWhichNeedUpdate")
    void testUpdateCryptoWalletAddress(User user, String newWallet) {

        User userBeforeUpdate = this.userRepository.saveAndFlush(user);
        assertNotNull(userBeforeUpdate);
        assertNotEquals((userBeforeUpdate.getCryptoWalletAddress()), newWallet);

        Long userID = (userBeforeUpdate.getId());
        this.userRepository.updateCryptoWalletAddress(userID, newWallet);
        this.userRepository.flush();

        User userAfterUpdate = this.userRepository.findById(userID).orElse(null);

        assertNotNull(userAfterUpdate);
        assertNotEquals(userBeforeUpdate, userAfterUpdate);
        assertNotEquals((userBeforeUpdate.getCryptoWalletAddress()), (userAfterUpdate.getCryptoWalletAddress()));
        assertEquals(userAfterUpdate.getCryptoWalletAddress(), newWallet);

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testUpdateCryptoWalletAddressWithNullOrEmptyInput(String wallet) {

        User userBeforeUpdate = this.userRepository.saveAndFlush((generateOnlyOneUser()));
        assertNotNull(userBeforeUpdate);

        this.userRepository.updateCryptoWalletAddress((userBeforeUpdate.getId()), wallet);
        this.userRepository.flush();

        String updateWallet = this.userRepository.getById((userBeforeUpdate.getId())).getCryptoWalletAddress();

        assertTrue((updateWallet == null || updateWallet.isEmpty()));

    }

    @RepeatedTest(10)
    @Rollback(true)
    void testUpdateCryptoWalletAddressWhichAddressAlreadyTaken() {
        User uOne = generateOnlyOneUser();
        User uTwo = generateOnlyOneUser();

        this.userRepository.save(uOne);
        this.userRepository.save(uTwo);

        String existWallet = uOne.getCryptoWalletAddress();

        Exception e = assertThrows(DataIntegrityViolationException.class,
                () -> this.userRepository.updateCryptoWalletAddress((uTwo.getId()), existWallet));

        assertNotNull(e);
        assertInstanceOf(DataIntegrityViolationException.class, e);
        assertNotEquals((uTwo.getCryptoWalletAddress()), existWallet);

    }

    public static Stream<Arguments> getUsersWhichHaveStatusDeparted() {
        return Stream.of(

                Arguments.of(
                        (new User(
                                "@" + new RandomString(5).nextString(),
                                "0x" + new RandomString(16).nextString(),
                                new RandomString(128).nextString(),
                                NetStatus.DEPARTED,
                                new RandomString(200).nextString().toUpperCase()

                        ))

                ),

                Arguments.of(
                        (new User(
                                "@" + new RandomString(5).nextString(),
                                "0x" + new RandomString(16).nextString(),
                                new RandomString(128).nextString(),
                                NetStatus.DEPARTED,
                                new RandomString(200).nextString().toUpperCase()

                        ))

                ),

                Arguments.of(
                        (new User(
                                "@" + new RandomString(5).nextString(),
                                "0x" + new RandomString(16).nextString(),
                                new RandomString(128).nextString(),
                                NetStatus.DEPARTED,
                                new RandomString(200).nextString().toUpperCase()

                        ))

                ),

                Arguments.of(
                        (new User(
                                "@" + new RandomString(5).nextString(),
                                "0x" + new RandomString(16).nextString(),
                                new RandomString(128).nextString(),
                                NetStatus.DEPARTED,
                                new RandomString(200).nextString().toUpperCase()

                        ))

                )

        );
    }

    @ParameterizedTest
    @MethodSource("getUsersWhichHaveStatusDeparted")
    void testUpdateNetStatus(User user) {
        User userBeforeStatusUpdate = this.userRepository.saveAndFlush(user);
        assertNotNull(userBeforeStatusUpdate);

        Long idUserToUpdate = userBeforeStatusUpdate.getId();

        this.userRepository.updateNetStatus(idUserToUpdate, NetStatus.ONLINE);
        this.userRepository.flush();

        NetStatus updateNetStatus = this.userRepository.getById(idUserToUpdate).getOnlineNetStatus();
        assertNotNull(updateNetStatus);
        assertTrue((updateNetStatus == NetStatus.ONLINE));
        assertTrue((userBeforeStatusUpdate.getOnlineNetStatus() != updateNetStatus));

    }

    // DELETE
    @Test
    void testDeleteById() {

        User userToSave = generateOnlyOneUser();
        this.userRepository.save(userToSave);

        Long userDeleteId = userToSave.getId();

        this.userRepository.deleteById(userDeleteId);

        assertFalse((this.userRepository.findById(userDeleteId).isPresent()));

    }

    @ParameterizedTest
    @NullSource
    @Rollback(true)
    void testDeleteByIdWithNullOrEmptyInput(Long invId) {

        User userToSave = generateOnlyOneUser();
        this.userRepository.save(userToSave);

        Long userDeleteId = userToSave.getId();

        assertThrows(InvalidDataAccessApiUsageException.class, () -> this.userRepository.deleteById(invId));
        assertTrue((this.userRepository.findById(userDeleteId).isPresent()));
    }


    @Rollback(true)
    @ParameterizedTest
    @ValueSource(longs = {12313 , -1 , 1_000_000 , -242 , -7_000})
    void testDeleteByIdWithInvalidIdArgument(Long invId){
        User userToSave = generateOnlyOneUser();
        this.userRepository.save(userToSave);


        assertThrows(EmptyResultDataAccessException.class, () -> this.userRepository.deleteById(invId));
        assertTrue((this.userRepository.findById((userToSave.getId())).isPresent()));


    }



    @Test
    void testDeleteByUserName() {

        User userToDelete = generateOnlyOneUser();
        assertNotNull(this.userRepository.save(userToDelete));

        String userDeleteUsername = userToDelete.getUserName();

        this.userRepository.deleteByUserName(userDeleteUsername);
        this.userRepository.flush();

        assertFalse( (this.userRepository.findByUserName(userDeleteUsername).isPresent()));


    }

    @ParameterizedTest
    @NullAndEmptySource
    void testDeleteByUserNameWithNullOrEmptySource(String invUsername) {
        User userToDelete = generateOnlyOneUser();
        assertNotNull(this.userRepository.save(userToDelete));

        this.userRepository.deleteByUserName(invUsername);
        this.userRepository.flush();

        assertTrue( (this.userRepository.findByUserName( (userToDelete.getUserName()) ).isPresent()));
        assertEquals( userToDelete , (this.userRepository.findByUserName( (userToDelete.getUserName()) ).get()));

    }

    @Test
    void testDeleteByUserNameWithOtherUsernames() {

        for (int i = 0; i < 10; i++) {

                assertNotNull(
                (this.userRepository.save(generateOnlyOneUser()))
                );
                
        }

        String randomUsername = new RandomString(5).nextString();
        this.userRepository.deleteByUserName(randomUsername);

        assertTrue( ( (this.userRepository.count()) == 10 ) );

    }

    @Test
    void testDeleteByCryptoWalletAddress() {

        User userToDelete = generateOnlyOneUser();
        String delteWallet = userToDelete.getCryptoWalletAddress();

        assertNotNull( (this.userRepository.save(userToDelete)) );

        this.userRepository.deleteByCryptoWalletAddress(delteWallet);

        assertFalse( (this.userRepository.findByCryptoWalletAddress(delteWallet).isPresent()) );

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testDeleteByCryptoWalletAddressWithNullOrEmptyInput(String nullOrEmptyWallet) {
        User userToDelete = generateOnlyOneUser();
        String deleteWallet = userToDelete.getCryptoWalletAddress();

        assertNotNull( (this.userRepository.save(userToDelete)) );

        this.userRepository.deleteByCryptoWalletAddress(nullOrEmptyWallet);

        assertTrue( (this.userRepository.findByCryptoWalletAddress(deleteWallet).isPresent()) );


    }

    @ParameterizedTest
    @ValueSource(strings = { "0xwoCOwKC0q2CjonLNclD112eCQ1d1ScvAcAfar211rfa",
            "0xwoCOwKC0q2CjIJbncv012cee234rtlD112eCQd1ScvAcAfar211rfa", "0xWWsacCwwvZ012e8C81xXqu12D31r1fDqwd" })
    void testDeleteByCryptoWalletAddressWithOtherWalletsAddresses(String notExistWallet) {
        User userToSave = generateOnlyOneUser();
        String deleteWallet = userToSave.getCryptoWalletAddress();

        assertNotNull( (this.userRepository.save(userToSave)) );

        this.userRepository.deleteByCryptoWalletAddress(notExistWallet);

        assertTrue( (this.userRepository.findByCryptoWalletAddress(deleteWallet).isPresent()) );
        assertEquals( userToSave , (this.userRepository.findByCryptoWalletAddress(deleteWallet).get()) );

    }

    @BeforeEach
    public void clearAll(){

        this.userRepository.deleteAll();
    }

}
