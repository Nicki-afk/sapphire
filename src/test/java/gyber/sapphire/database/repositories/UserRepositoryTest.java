package gyber.sapphire.database.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
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


    private List<String>usersHash = new ArrayList<>();




    @BeforeEach
    public void moreUsers(){


        for(int x = 0; x < 2; x++ ){


         usersHash
            .add(

                this.userRepository
                .save(
                    (
                    new User(
                        ( "@" + new RandomString(5).nextString()) , 
                        ("0x" + new RandomString(16).nextString()) , 
                        (new RandomString(128).nextString()) , 
                        NetStatus.ONLINE , 
                        (new RandomString(200).nextString().toUpperCase())
                    ))
                ).getHashUserFile()
            );


            System.out.println("USER SAVE ");



        }

        System.out.println(
            "\n\n\n\n Users Saved  : "
            + 
            (this.userRepository.count())
            +
            "\n\n\n\n"
        );


  
    }


    public String saveAndGetWallet(){
       
        User localUser = 
                this.userRepository
                .save(
                    (
                    new User(
                        ( "@" + new RandomString(5).nextString()) , 
                        ("0x" + new RandomString(16).nextString()) , 
                        (new RandomString(128).nextString()) , 
                        NetStatus.ONLINE , 
                        (new RandomString(200).nextString().toUpperCase())
                    ))
                );

        this.usersHash.add(localUser.getHashUserFile());
        
        return (localUser.getCryptoWalletAddress());
        
    }





   



    @Test
    void testFindByBetaTestKey() {

    }

    @Test
    void testFindByCryptoWalletAddress() {
        
        assertNotNull(
            (
                this.userRepository
                .findByCryptoWalletAddress( (saveAndGetWallet()) )
            )
        );

    }

    @Test
    void testFindByHashUserFile() {
        for (int i = 0; i < this.usersHash.size(); i++) {

            assertNotNull(
               (this.userRepository.findByHashUserFile((this.usersHash.get(i))).get())

            );
            
        }

    }

    @Test
    void testFindById() {assertNotNull((userRepository.findById(1L).get()));}

    @Test
    void testFindByUserName() {

    }
}

