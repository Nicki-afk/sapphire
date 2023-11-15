package gyber.sapphire.database.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gyber.sapphire.messaging.Message;

import java.util.List;
import java.util.Optional;


public interface MessageRepository extends JpaRepository<Message , Long>{

    Optional<Message> findByMsgID(Long msgID);


    @Query("SELECT m FROM Message m WHERE m.chat.chatId = :chatId ORDER BY m.sentAt DESC")
    Page<Message> getLast20Messages(Long chatId , Pageable pageable);


    @Query("SELECT m FROM Message m WHERE m.chat.chatId = :chatId")
    List<Message> getChatMessages(Long chatId);

    // TODO : Add a method that can download messages from the range. 
    //       For example, download 20 messages starting from message number 12




}
