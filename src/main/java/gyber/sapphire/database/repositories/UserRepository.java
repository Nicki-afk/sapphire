package gyber.sapphire.database.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gyber.sapphire.profile.User;

public interface UserRepository extends JpaRepository<User , Long>{

    Optional<User> findById(Long id);
    Optional<User> findByUserName(String userName);
    Optional<User> findByCryptoWalletAddress(String cryptoWalletAddress);
    
}
