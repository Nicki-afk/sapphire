package gyber.sapphire.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/up")
public class UpdateController {


    @PutMapping("/username")
    public ResponseEntity updateUsernameById(
        @RequestParam("id") Long id , 
        @RequestParam("new") String newUsername 
        ){
        // ... 
        return ResponseEntity.ok().build();

    }

    @PutMapping("/hash")
    public ResponseEntity updateHashUserFileById(
        @RequestParam("id") Long id , 
        @RequestParam("new") String hashFile
    ){

        // ... 
        return ResponseEntity.ok().build();
    }


    @PutMapping("/stat")
    public ResponseEntity updateUserNetStatus(
        @RequestParam("id") Long id , 
        @RequestParam("status") String status 
        
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
        @RequestParam("id") Long id ,
        @RequestParam("new") String newWalletAddress  , 
        @RequestHeader("Public-key") String base64PublicKey  ,
        @RequestHeader("Wallet-address") String base64WalletAddress  ,
        @RequestHeader("Signature") String base64UserSignature
    
    ){

        /*
         * zeroing old public key 
         * update public key 
         */

        // ... 
        return ResponseEntity.ok().build();

    }


    
}

@Controller
@RequestMapping("/chat/up")
class UpdateChatController{


    /*
     * Для переименнования групповых чатов 
     * - Добавить проверку на администратора 
     */
    @PutMapping("/name")
    public ResponseEntity updateChatName(
        @RequestParam("id") Long id , 
        @RequestParam("new") String newChatName
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
        @RequestParam("id") Long id ,
        @RequestParam("hash") String hashImageLogo 

    ){

    // ... 
    return ResponseEntity.ok().build();

    }

    @PutMapping("/part")
    public ResponseEntity updateParticipantsInChatById(
        @RequestParam("id") Long id , 
        @RequestParam("plus") Integer plusPart
    ){
        // ...

        return ResponseEntity.ok().build();

    }



}
