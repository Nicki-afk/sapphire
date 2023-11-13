package gyber.sapphire.database.repositories;

import java.util.Optional;

import gyber.sapphire.profile.User;
import gyber.sapphire.profile.NetStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface UserRepository extends JpaRepository<User, Long> {

    // READ
    Optional<User> findById(Long id);

    Optional<User> findByUserName(String userName);


    @Modifying(clearAutomatically  = true)
    @Query("UPDATE User u SET u.userName = :newUserName WHERE u.id = :userId")
    void updateUserName(Long userId, String newUserName);

 
    @Modifying(clearAutomatically  = true)
    @Query("UPDATE User u SET u.onlineNetStatus = :newStatus WHERE u.id = :userId")
    void updateNetStatus(Long userId, @Param("newStatus") NetStatus newStatus);

    // DELETE
    void deleteById(Long id);

    void deleteByUserName(String username);

    // void deleteByCryptoWalletAddress(String cryptoWalletAddress);

}