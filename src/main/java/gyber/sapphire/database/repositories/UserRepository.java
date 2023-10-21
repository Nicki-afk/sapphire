package gyber.sapphire.database.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

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

    Optional<User> findByCryptoWalletAddress(String cryptoWalletAddress);

    Optional<User> findByHashUserFile(String hashUserFile);

    @Query("SELECT user FROM User user WHERE user.betaTestKey.key = :key")
    Optional<User> findByBetaTestKey(String key);

    // UPDATE
    @Modifying
    @Query("UPDATE User u SET u.hashUserFile = :file WHERE u.id = :uid")
    void updateHashUserFile(@Param(value = "uid") Long userId, @Param(value = "file") String file);

    @Modifying
    @Query("UPDATE User u SET u.userName = :newUserName WHERE u.id = :userId")
    void updateUserName(Long userId, String newUserName);

    @Modifying
    @Query("UPDATE User u SET u.cryptoWalletAddress = :newCryptoWalletAddress WHERE u.id = :userId")
    void updateCryptoWalletAddress(Long userId, String newCryptoWalletAddress);

    @Modifying
    @Query("UPDATE User u SET u.onlineNetStatus = :newStatus WHERE u.id = :userId")
    void updateNetStatus(Long userId, @Param("newStatus") NetStatus newStatus);

    // DELETE
    void deleteById(Long id);

    void deleteByUserName(String username);

    void deleteByCryptoWalletAddress(String cryptoWalletAddress);

}