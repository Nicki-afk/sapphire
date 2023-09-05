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

import gyber.websocket.models.UserCustomDetailsService;
import gyber.websocket.security.authenticate.tokenManagement.JwtService;
import gyber.websocket.security.authenticate.tokenManagement.TokenPairObject;
import io.jsonwebtoken.JwtException;
import gyber.websocket.controllers.exceptions.TokenLocalStorageException;
import gyber.websocket.controllers.exceptions.ErrorRestResponse;
import gyber.websocket.models.NetStatus;
import gyber.websocket.models.User;
import gyber.websocket.models.UserCustomDetails;



@Controller
@RequestMapping("/auth")
public class AuthenticateController {


    @Autowired
    private UserCustomDetailsService userIPFSCustomDetailsService;


    @Autowired
    private JwtService jwtService;

    // Map ( singuture , cryptoWalletAdress , pub key)
    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody Map<String, String>userAuthData){
        
        String signature = userAuthData.get("signature");  
        String cryptoWalletAddress = userAuthData.get("crypto_wallet_address");

      //  UserDetails userDetails = userIPFSCustomDetailsService.loadUserByUsername(cryptoWalletAddress);
      UserCustomDetails userDetails = this.userIPFSCustomDetailsService.loadUserByCryptowalletAddress(cryptoWalletAddress);


        UsernamePasswordAuthenticationToken userCredentalsData = new UsernamePasswordAuthenticationToken(userDetails.getUsername() , null);
        SecurityContextHolder.getContext().setAuthentication(userCredentalsData);

        String token = jwtService.createToken(userDetails);


        return ResponseEntity.ok(
            Map.of("jwt" , token , "time" , LocalDateTime.now())
        );


    }

    @GetMapping
    public ResponseEntity simpleResponse() throws TokenLocalStorageException {
        User user = new User(2L, "@nic_ko", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w", NetStatus.ONLINE, "GpVlRPpkMY5e0IuMFrt00g3ioZFi1QKlMtKTtZPso0Jx2I1w0w");
        throw new TokenLocalStorageException("Jwt Validation not passed" , new Exception() , user , new TokenPairObject());
       // return ResponseEntity.ok("HI");
    }

    
    
}
