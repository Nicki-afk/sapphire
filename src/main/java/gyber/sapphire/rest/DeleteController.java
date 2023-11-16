package gyber.sapphire.rest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Validated
@Controller
@RequestMapping("/del")
public class DeleteController {



    @DeleteMapping("/usr")
    public ResponseEntity deleteUser(
        @NotNull
        @Min(1)
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
        @RequestParam("chatId")  @NotNull @Min(1) Long chatId  , 
        @RequestParam("adminId") @NotBlank @Min(1) Long adminId
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
        @RequestParam("chatId") @NotNull @Min(1) Long chatId , 
        @RequestParam("delId") @NotNull @Min(1) Long deleteUserId , 
        @RequestParam("admin") @NotNull @Min(1) Long adminId
    ){
        // ... 

        return ResponseEntity.ok().build();

    }



}