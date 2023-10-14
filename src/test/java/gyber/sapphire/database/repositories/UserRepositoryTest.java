package gyber.sapphire.database.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import gyber.sapphire.profile.NetStatus;
import gyber.sapphire.profile.User;


@DataJpaTest
@TestPropertySource("classpath:test-source.properties")
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private  UserRepository userRepository;


    @BeforeEach
    public void moreUsers(){
        User user = new User( "@nic_ko", "0xLK7HC8w99c9qwkjhebnlc9Qd1", "kjcwbiuofhwiufhbwkv", NetStatus.ONLINE,  "wouhiuhciuhech92hdiuhgefigijhgx9ubwxihgqufgq");
        this.userRepository.save(user);

        System.out.println("\n\n\n " + "OK " + "\n\n\n");
    }
  

   



    @Test
    void testFindByBetaTestKey() {

    }

    @Test
    void testFindByCryptoWalletAddress() {

    }

    @Test
    void testFindByHashUserFile() {

    }

    @Test
    void testFindById() {
      //  moreUsers();
        assertNotNull((userRepository.findById(1L).get()));

    
    }

    @Test
    void testFindByUserName() {

    }
}
