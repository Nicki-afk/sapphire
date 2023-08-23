package gyber.websocket.security.authenticate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gyber.websocket.models.UserIPFSModel;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService implements TokenAuthenticate {


   @Value("${jwt.token.validity.time}") private long validTime;
   @Value("${sign.token.sign}")  private String singature;
   







    @Override
    public String createToken(UserIPFSModel userIPFSModel) {
       

        LocalDateTime timeToSet = LocalDateTime.now().plusHours(4);
        Date dateToSet = Date.from(timeToSet.atZone(ZoneId.systemDefault()).toInstant());

        Map<String , Object>payload = new HashMap<>();
        payload.put("userId", userIPFSModel.getId());
        payload.put("username", userIPFSModel.getUserName());





       return Jwts.
             builder()
            .setExpiration(dateToSet)
            .signWith(SignatureAlgorithm.HS256 , singature)
            .setSubject("JWT-DETAILS")
            .setClaims(payload)
            .setIssuedAt(new Date())
            .compact();

    }








    @Override
    public boolean validateToken(String token) {
            try {
            Jwts.parser().setSigningKey(this.singature).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
        }   

        return false;
    }


}
