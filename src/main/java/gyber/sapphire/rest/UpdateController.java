package gyber.sapphire.rest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gyber.sapphire.validation.IsBase64;

@Validated
@Controller
@RequestMapping("/up")
public class UpdateController {


    @PutMapping("/username")
    public ResponseEntity updateUsernameById(
        @RequestParam("id") @NotNull @Min(1) Long id , 
        @RequestParam("new") @NotBlank @Size(min = 6 , max = 8) String newUsername 
        ){
        // ... 
        return ResponseEntity.ok().build();

    }

    @PutMapping("/hash")
    public ResponseEntity updateHashUserFileById(
        @RequestParam("id") @NotNull @Min(1) Long id , 
        @RequestParam("new") @NotBlank  String hashFile
    ){

        // ... 
        return ResponseEntity.ok().build();
    }


    @PutMapping("/stat")
    public ResponseEntity updateUserNetStatus(
        @RequestParam("id") @NotNull @Min(1) Long id , 
        @RequestParam("status") @NotBlank String status 
        
        ){

            // ... 
        return ResponseEntity.ok().build();  
    }

    /*
     * Для смены адреса крипто кошелька необходимо 
     * подписать действие и перезайти в аккаунт 
     */
    @PutMapping("/wallet")
    public ResponseEntity updateUserWalletAddres(
        @RequestParam("id") @NotNull @Min(1) Long id ,
        @RequestParam("new") @NotBlank String newWalletAddress  , 
        @RequestHeader("Update-Message") @NotBlank @IsBase64 String base64UpdateMessage  ,
        @RequestHeader("Wallet-address") @NotBlank @IsBase64  String base64WalletAddress  ,
        @RequestHeader("Signature") @NotBlank @IsBase64 String base64UserSignature
    
    ){

        /*
         * zeroing old public key 
         * update public key 
         */

        // ... 
        return ResponseEntity.ok().build();

    }


    
}

@Validated
@Controller
@RequestMapping("/chat/up")
class UpdateChatController{


    /*
     * Для переименнования групповых чатов 
     * - Добавить проверку на администратора 
     */
    @PutMapping("/name")
    public ResponseEntity updateChatName(
        @RequestParam("id") @NotNull @Min(1) Long id , 
        @RequestParam("new") @NotNull @Size(min = 6 , max = 8) String newChatName
    ){
        // ... 
        return ResponseEntity.ok().build();

    }


    /*
     * Для добовления лого для чата , необходимо
     * отправить ссылку на файл из сети IPFS
     */
    @PutMapping("/logo")
    public ResponseEntity updateChatLogoById(
        @RequestParam("id") @NotBlank @Min(1) Long id ,
        @RequestParam("hash") @NotBlank String hashImageLogo 

    ){

    // ... 
    return ResponseEntity.ok().build();

    }

    /*
     * Добавление новых участников. Передается 
     * id того кто добавляет , и id того кого 
     * добавляют 
     */
    @PutMapping("/part")
    public ResponseEntity updateParticipantsInChatById(
        @RequestParam("id") @NotBlank @Min(1) Long id , 
        @RequestParam("plus") @NotBlank @Min(1) Integer plusPart
    ){
        // ...

        return ResponseEntity.ok().build();

    }



}
