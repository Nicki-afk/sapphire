package gyber.websocket.models.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gyber.websocket.security.beta.BetaTestKey;

@Repository
public interface BetaTestKeyRepository extends JpaRepository<BetaTestKey , BetaTestKey> {

    
}
