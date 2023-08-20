package gyber.websocket.models.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gyber.websocket.models.User;
import gyber.websocket.models.UserIPFSModel;

public interface UserRepository extends JpaRepository<Integer , User>{

    Optional<UserIPFSModel> findById(Long id);
    Optional<UserIPFSModel> findByUsername(String userName);
    Optional<UserIPFSModel> findByCryptoWalletAddress(String cryptoWalletAddress);
    
}
