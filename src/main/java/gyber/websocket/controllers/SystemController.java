package gyber.websocket.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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


    
}
