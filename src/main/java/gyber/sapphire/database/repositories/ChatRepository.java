package gyber.sapphire.database.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import gyber.sapphire.messaging.Chat;
import gyber.sapphire.profile.User;

import java.util.List;


public interface ChatRepository extends JpaRepository<Chat , Long> {    

    // READ
    Optional<Chat> findByChatId(Long chatId);

    List<Chat> findByNameChat(String nameChat);

    @Query("SELECT c FROM Chat c JOIN c.participantsChat p WHERE p.id = :usrId")
    List<Chat> getUserChatList(Long usrId);

    @Query("SELECT u FROM User u JOIN u.chats c WHERE c.chatId = :idChat")
    List<User> getParticipantsChat(Long idChat);



    // UPDATE 
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Chat c SET c.nameChat = :newChatName WHERE c.chatId = :idChat")
    void updateChatName(String newChatName , Long idChat);


    default void updateChatParticipants(Long chatId , User newUser){

        Chat chat = getById(chatId);
        chat.getParticipantsChat().add(newUser);
        save(chat);


    }

    


    
}
