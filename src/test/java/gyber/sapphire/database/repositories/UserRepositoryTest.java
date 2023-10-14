package gyber.sapphire.database.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;


@DataJpaTest
@TestPropertySource("classpath:test-source.properties")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;



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

    }

    @Test
    void testFindByUserName() {

    }
}
