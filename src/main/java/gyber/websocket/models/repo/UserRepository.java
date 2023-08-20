package gyber.websocket.models.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import gyber.websocket.models.User;
import gyber.websocket.models.UserIPFSModel;

public interface UserRepository extends JpaRepository<Integer , User>{

    UserIPFSModel findById(Long id);
    UserIPFSModel findByUsername(String userName);
    UserIPFSModel findByCryptoWalletAddress(String cryptoWalletAddress);
    
}
