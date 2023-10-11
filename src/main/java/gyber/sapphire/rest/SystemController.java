package gyber.sapphire.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedHashMap;
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

import gyber.sapphire.beta.BetaTestKey;
import gyber.sapphire.beta.BetaTestKeyManager;
import gyber.sapphire.errors.BetaTestKeyException;

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


        Map<String , Object >mapToResponse = new LinkedHashMap<>();
        
        mapToResponse.put("date_generate" ,LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss")) );
        mapToResponse.put("tsar_key", tsarKey);
        mapToResponse.put("quantity", quantity);
        mapToResponse.put("keys", arrKeys);
        
        

        return ResponseEntity.ok(
            (
              mapToResponse
               
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
