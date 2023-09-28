package gyber.sapphire.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/del")
public class DeleteController {



    @DeleteMapping("/usr")
    public ResponseEntity deleteUser(
        @RequestParam("id") Long id 
    ){
        // ... 
        return ResponseEntity.ok().build();
    }


}

@Controller
@RequestMapping("/del/chat")
class DeleteChatController{

    @DeleteMapping
    public ResponseEntity deleteChat(
        @RequestParam("chatId") Long chatId  
     ){
        // ... 
        return ResponseEntity.ok().build();
    }


    /*
     * В параметрах передается айди чата 
     * айди удаляемого пользоваьтеля , а также 
     * айди человека который удалил. Удалить может только 
     * администратор 
     */
    @DeleteMapping("/usr")
    public ResponseEntity deleteUserInChat(
        @RequestParam("chatId") Long chatId , 
        @RequestParam("delId") Long deleteUserId , 
        @RequestParam("admin") Long adminId
    ){
        // ... 

        return ResponseEntity.ok().build();

    }



}