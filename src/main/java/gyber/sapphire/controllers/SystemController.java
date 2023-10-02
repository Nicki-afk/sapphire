package gyber.sapphire.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gyber.sapphire.exceptions.BetaTestKeyException;
import gyber.sapphire.security.beta.BetaTestKey;
import gyber.sapphire.security.beta.BetaTestKeyManager;

@Validated
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    public BetaTestKeyManager keyManager;

    @GetMapping("/keys")
    public ResponseEntity getMoreKeys(
        @RequestParam(name = "tsar") @NotBlank @Size(min = 100  , max = 100) String tsarKey , 
        @RequestParam(name = "qu") @NotNull @Min(1) @Max(30) Integer quantity
        ) throws BetaTestKeyException{
        
        BetaTestKey[] arrKeys = this.keyManager.generateMoreKeys(quantity, tsarKey);
        this.keyManager.saveMoreKeys(arrKeys);

        return ResponseEntity.ok(
            (
                Map.of("date_generate" , LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss")) , "tsar_key" , tsarKey , "quantity" , quantity , "keys" , arrKeys)
            )
        );

    }


    @DeleteMapping("/key/del")
    public ResponseEntity deleteKey(
        @RequestParam("tsar") @NotBlank @Size(min = 100 , max = 100) String tsarKey , 
        @RequestParam("keyId") @NotNull @Min(1)  Long keyId 
    ){

        // ... 

        return ResponseEntity.ok().build();

    }


    /*
     * Удаляет ключи которые были сгенерированы в 
     * определенную дату 
     */
    @DeleteMapping("/key/del/date")
    public ResponseEntity deleteKeyByDate(
        @RequestParam("tsar") @NotBlank @Size(min = 100 , max = 100) String tsarKey , 
        @RequestHeader("Date-Key-Argument") @NotBlank   String date 
    ){

        // ... 
        return ResponseEntity.ok().build();

    }


    @DeleteMapping("/key/del/all")
    public ResponseEntity deleteAllKeys(
        @RequestParam("tsar") @NotBlank @Size(min = 100 , max = 100) String tsarKey
    ){

        /// ... 

        return ResponseEntity.ok().build();
    }


    
}
