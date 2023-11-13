package gyber.sapphire.database.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import gyber.sapphire.messaging.Chat;
import java.util.List;


public interface ChatRepository extends JpaRepository<Chat , Long> {    

    // READ
    Optional<Chat> findByChatId(Long chatId);

    List<Chat> findByNameChat(String nameChat);


    
}
