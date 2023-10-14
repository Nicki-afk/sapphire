package gyber.sapphire.database.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gyber.sapphire.profile.User;
import java.util.List;
import gyber.sapphire.beta.BetaTestKey;



public interface UserRepository extends JpaRepository<User , Long>{

    Optional<User> findById(Long id);
    Optional<User> findByUserName(String userName);
    Optional<User> findByCryptoWalletAddress(String cryptoWalletAddress);

    Optional<User> findByHashUserFile(String hashUserFile);


    @Query("SELECT user FROM User user WHERE user.betaTestKey.key = :key")
    Optional<User> findByBetaTestKey(String key);



    
}