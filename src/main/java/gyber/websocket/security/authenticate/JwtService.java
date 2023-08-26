package gyber.websocket.security.authenticate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gyber.websocket.models.UserIPFSDetails;
import gyber.websocket.models.UserIPFSModel;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService implements TokenAuthenticate {


   @Value("${jwt.token.validity.time}") private long validTime;
   @Value("${sign.token.sign}")  private String singature;
   







    @Override
    public String createToken(UserIPFSDetails userDetails) {

        if(userDetails == null || userDetails.getId() == null || userDetails.getUsername() == null || 
                            userDetails.getUsername().isEmpty() || userDetails.getCryptoWalletAddress().isEmpty() || userDetails.getCryptoWalletAddress() == null){
            throw new NullPointerException("UserDetails object is null , or filelds in this object is null or empty");

        }
       

        LocalDateTime timeToSet = LocalDateTime.now().plusHours(4);
        Date dateToSet = Date.from(timeToSet.atZone(ZoneId.systemDefault()).toInstant());

        Map<String , Object>payload = new HashMap<>();
        payload.put("userId", userDetails.getId());
        payload.put("username", userDetails.getUsername());

       return   Jwts.
                         builder()
                        .signWith(SignatureAlgorithm.HS256 , singature)
                        .setSubject("jwt")
                        .setClaims(payload)
                        .setHeaderParam("type", "JWT")
                        .setIssuedAt(new Date())
                        .setIssuer("sapphire-messanger-server")
                        .setExpiration(dateToSet)
                        .compact();

                  

    }








    @Override
    public boolean validateToken(String token) {
        try {
            
              Jwts.parser().setSigningKey(this.singature).parseClaimsJws(token).getBody();
              return true;
        
        } catch (JwtException | IllegalArgumentException e) {
            //e.printStackTrace();
        }   

        return false;
    }


    // TODO : Добавить проверку на валидацию токена. Добавить ошибки , обработать исключения 
    public String getUserNameInToken(String token){



        return Jwts
                    .parser()
                    .setSigningKey(this.singature)
                    .parseClaimsJws(token)
                    .getBody()
                    .get("username", String.class);

    }


}
