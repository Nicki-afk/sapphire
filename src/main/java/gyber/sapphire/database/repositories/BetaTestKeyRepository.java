package gyber.sapphire.database.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gyber.sapphire.beta.BetaTestKey;

@Repository
public interface BetaTestKeyRepository extends JpaRepository<BetaTestKey , Long> {

    Optional<BetaTestKey> findById(Long id);
    Optional<BetaTestKey> findByKey(String key);
    boolean existsByKey(String key);

    
}
