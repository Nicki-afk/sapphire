package gyber.websocket.controllers;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gyber.websocket.models.UserIPFSCustomDetailsService;
import gyber.websocket.models.UserIPFSDetails;
import gyber.websocket.security.authenticate.JwtService;



@Controller
@RequestMapping("/auth")
public class AuthenticateController {


    @Autowired
    private UserIPFSCustomDetailsService userIPFSCustomDetailsService;


    @Autowired
    private JwtService jwtService;

    // Map ( singuture , cryptoWalletAdress , pub key)
    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody Map<String, String>userAuthData){
        
        String signature = userAuthData.get("signature");  
        String cryptoWalletAddress = userAuthData.get("crypto_wallet_address");

      //  UserDetails userDetails = userIPFSCustomDetailsService.loadUserByUsername(cryptoWalletAddress);
      UserIPFSDetails userDetails = this.userIPFSCustomDetailsService.loadUserByCryptowalletAddress(cryptoWalletAddress);


        UsernamePasswordAuthenticationToken userCredentalsData = new UsernamePasswordAuthenticationToken(userDetails.getUsername() , null);
        SecurityContextHolder.getContext().setAuthentication(userCredentalsData);

        String token = jwtService.createToken(userDetails);


        return ResponseEntity.ok(
            Map.of("jwt" , token , "time" , LocalDateTime.now())
        );


    }

    @GetMapping
    public ResponseEntity simpleResponse(){
        return ResponseEntity.ok("HI");
    }
    
}
