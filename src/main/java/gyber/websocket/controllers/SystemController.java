package gyber.websocket.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gyber.websocket.exceptions.BetaTestKeyException;
import gyber.websocket.security.beta.BetaTestKey;
import gyber.websocket.security.beta.BetaTestKeyManager;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    public BetaTestKeyManager keyManager;

    @GetMapping("/keys")
    public ResponseEntity getMoreKeys(
        @RequestParam(name = "tsar") String tsarKey , 
        @RequestParam(name = "qu") Integer quantity
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
        @RequestParam("tsar") String tsarKey , 
        @RequestParam("keyId") Long keyId 
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
        @RequestParam("tsar") String tsarKey , 
        @RequestParam("date") String date 
    ){

        // ... 
        return ResponseEntity.ok().build();

    }


    @DeleteMapping("/key/del/all")
    public ResponseEntity deleteAllKeys(
        @RequestParam("tsar") String tsarKey
    ){

        /// ... 

        return ResponseEntity.ok().build();
    }


    
}
