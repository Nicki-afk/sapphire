package gyber.websocket.controllers;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import gyber.websocket.models.UserCustomDetailsService;
import gyber.websocket.models.repo.UserRepository;
import gyber.websocket.security.authenticate.signature.SignatureChecker;
import gyber.websocket.security.authenticate.tokenManagement.JwtService;
import gyber.websocket.security.authenticate.tokenManagement.TokenLocalStorageManager;
import gyber.websocket.security.authenticate.tokenManagement.TokenPairObject;
import io.jsonwebtoken.JwtException;
import gyber.websocket.exceptions.ErrorRestResponse;
import gyber.websocket.exceptions.TokenLocalStorageException;
import gyber.websocket.models.NetStatus;
import gyber.websocket.models.User;
import gyber.websocket.models.UserCustomDetails;

@Controller
@RequestMapping("/auth")
public class AuthenticateController {

    @Autowired
    private UserCustomDetailsService userCustomDetailsService;

    @Autowired
    private UserRepository repository;


    @Autowired
    private TokenLocalStorageManager tokenManager;



//     @PostMapping
//     public ResponseEntity authenticateUser(
//             @RequestHeader("Authorization") String authenticateData) throws TokenLocalStorageException {

//         String decodeBase64String = new String(Base64.getDecoder()
//                 .decode((authenticateData.substring(authenticateData.indexOf(" "))).trim().getBytes()));
//         String[] arr = decodeBase64String.split(":");


//         User user = this.repository.findByCryptoWalletAddress(arr[1]).orElse(null);
//         UserCustomDetails userDetails = this.userCustomDetailsService.loadUserByCryptowalletAddress(arr[1]);

//         UsernamePasswordAuthenticationToken userCredentalsData = new UsernamePasswordAuthenticationToken(
//                 userDetails.getUsername(), null);
//         SecurityContextHolder.getContext().setAuthentication(userCredentalsData);

//         TokenPairObject tokenPairObject = this.tokenManager.addTokenPairForUser(user);

//         return ResponseEntity.ok(
//                 Map.of("pair_object" , tokenPairObject ,  "time", LocalDateTime.now()));

//     }


    
    @PostMapping
    public ResponseEntity authenticateUser(
        @RequestHeader("Wallet") String base64WalletAddress ,
        @RequestHeader("Signature") String base64Signature , 
        @RequestHeader("Authenticate-Message") String base64Message  
    ) throws UnsupportedEncodingException, TokenLocalStorageException{


        String decodeWalletAddress = new String( Base64.getDecoder().decode((base64WalletAddress.getBytes())));

      
        boolean signatureConfirmed = SignatureChecker
                                        .builder()
                                        .setInputBase64Signature(base64Signature)
                                        .setInputBase64Message(base64Message)
                                        .setInputBase64WalletAddress(base64WalletAddress)
                                        .copyBytes()
                                        .createSignatureObject()
                                        .recoveryPublicKey()
                                        .recoveryWalletAddress()
                                        .build()
                                        .verifySignature();


        if(!this.repository.findByCryptoWalletAddress(decodeWalletAddress).isPresent()){
           return ResponseEntity
                       .status(401)
                       .header("X-Auth-Failure-Reason", "des=\"User not found at this wallet address\"")
                       .build();
        }else if(!signatureConfirmed){
                return ResponseEntity
                       .status(401)
                       .header("X-Auth-Failure-Reason", "des=\"The user failed signature verification\"")
                       .build();

        }

        UserCustomDetails userDetails = this.userCustomDetailsService.loadUserByCryptowalletAddress(decodeWalletAddress);


        UsernamePasswordAuthenticationToken userCredentalsData = new UsernamePasswordAuthenticationToken(
        userDetails.getUsername(), null);

        SecurityContextHolder.getContext().setAuthentication(userCredentalsData);

        
        TokenPairObject tokenPairObject = this.tokenManager.addTokenPairForUser((this.repository.findByCryptoWalletAddress(decodeWalletAddress).get()));

        return ResponseEntity.ok().body(tokenPairObject);

    }

    @GetMapping
    public ResponseEntity getAuthenticateInfo() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("WWW-Authenticate",
                "GyberAuth realm=\"Gyber Authenticate\", instructions=\"Enter the public key, your signature and your wallet address.\"");
        return ResponseEntity.status(401)
                .headers(httpHeaders)
                .build();

    }

}
