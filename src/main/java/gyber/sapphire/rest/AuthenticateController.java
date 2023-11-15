package gyber.sapphire.rest;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import gyber.sapphire.authentication.signature.SignatureChecker;
import gyber.sapphire.authentication.tokens.JwtService;
import gyber.sapphire.authentication.tokens.TokenLocalStorageManager;
import gyber.sapphire.authentication.tokens.TokenPairObject;
import gyber.sapphire.database.repositories.UserRepository;
import gyber.sapphire.errors.ErrorRestResponse;
import gyber.sapphire.errors.TokenLocalStorageException;
import gyber.sapphire.profile.NetStatus;
import gyber.sapphire.profile.User;
import gyber.sapphire.profile.UserCustomDetails;
import gyber.sapphire.profile.UserCustomDetailsService;
import gyber.sapphire.validation.IsBase64;
import io.jsonwebtoken.JwtException;

@Validated
@Controller
@RequestMapping("/auth")
public class AuthenticateController {

    @Autowired
    private UserCustomDetailsService userCustomDetailsService;

    @Autowired
    private UserRepository repository;


    @Autowired
    private TokenLocalStorageManager tokenManager;



        

 
    @PostMapping
    public ResponseEntity authenticateUser(
        @RequestHeader("Wallet") @NotBlank @IsBase64 String base64WalletAddress ,
        @RequestHeader("Signature") @NotBlank @IsBase64 String base64Signature , 
        @RequestHeader("Authenticate-Message") @NotBlank @IsBase64 String base64Message  
    ) throws UnsupportedEncodingException, TokenLocalStorageException{

  // String decodeWalletAddress = new String( Base64.getDecoder().decode((base64WalletAddress.getBytes())));

      
        // boolean signatureConfirmed = SignatureChecker
        //                                 .builder()
        //                                 .setInputBase64Signature(base64Signature)
        //                                 .setInputBase64Message(base64Message)
        //                                 .setInputBase64WalletAddress(base64WalletAddress)
        //                                 .copyBytes()
        //                                 .createSignatureObject()
        //                                 .recoveryPublicKey()
        //                                 .recoveryWalletAddress()
        //                                 .build()
        //                                 .verifySignature();


        // if(!this.repository.findByCryptoWalletAddress(decodeWalletAddress).isPresent()){
        //    return ResponseEntity
        //                .status(401)
        //                .header("X-Auth-Failure-Reason", "des=\"User not found at this wallet address\"")
        //                .build();
        // }else if(!signatureConfirmed){
        //         return ResponseEntity
        //                .status(401)
        //                .header("X-Auth-Failure-Reason", "des=\"The user failed signature verification\"")
        //                .build();

        // }

        // UserCustomDetails userDetails = this.userCustomDetailsService.loadUserByCryptowalletAddress(decodeWalletAddress);


        // UsernamePasswordAuthenticationToken userCredentalsData = new UsernamePasswordAuthenticationToken(
        // userDetails.getUsername(), null);

        // SecurityContextHolder.getContext().setAuthentication(userCredentalsData);

        
        // TokenPairObject tokenPairObject = this.tokenManager.addTokenPairForUser((this.repository.findByCryptoWalletAddress(decodeWalletAddress).get()));

      
        return ResponseEntity.ok().build();

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
