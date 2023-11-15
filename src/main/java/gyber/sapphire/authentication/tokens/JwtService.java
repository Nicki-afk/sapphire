package gyber.sapphire.authentication.tokens;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gyber.sapphire.profile.User;
import gyber.sapphire.profile.UserCustomDetails;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService implements TokenAuthenticate {


   private Date expirationDate ;
   @Value("${jwt.token.sign}")  private String singature;
   







    @Override
    public String createToken(UserCustomDetails userCustomDetails) {

        if(userCustomDetails == null || userCustomDetails.getId() == null || userCustomDetails.getUsername() == null || 
                            userCustomDetails.getUsername().isEmpty() ){
            throw new NullPointerException("UserDetails object is null , or filelds in this object is null or empty");

        }
       

        Map<String , Object>payload = new HashMap<>();
        payload.put("userId", userCustomDetails.getId());
        payload.put("username", userCustomDetails.getUsername());

        this.expirationDate = Date.from(LocalDateTime.now().plusHours(4).atZone(ZoneId.systemDefault()).toInstant());

       return   Jwts.
                         builder()
                        .signWith(SignatureAlgorithm.HS256 , singature)
                        .setSubject("jwt")
                        .setClaims(payload)
                        .setHeaderParam("type", "JWT")
                        .setIssuedAt(new Date())
                        .setIssuer("sapphire-messanger-server")
                        .setExpiration(this.expirationDate)
                        .compact();

                  

    }


    // for test
    public String testCreateJwt(UserCustomDetails userCustomDetails , long seconds){

        if(userCustomDetails == null || userCustomDetails.getId() == null || userCustomDetails.getUsername() == null || 
                            userCustomDetails.getUsername().isEmpty() ){
            throw new NullPointerException("UserDetails object is null , or filelds in this object is null or empty");

        }
       


        Map<String , Object>payload = new HashMap<>();
        payload.put("userId", userCustomDetails.getId());
        payload.put("username", userCustomDetails.getUsername());

        this.expirationDate = Date.from(LocalDateTime.now().plusSeconds(seconds).atZone(ZoneId.systemDefault()).toInstant());

        return   Jwts.
                         builder()
                        .signWith(SignatureAlgorithm.HS256 , singature)
                        .setSubject("jwt")
                        .setClaims(payload)
                        .setHeaderParam("type", "JWT")
                        .setIssuedAt(new Date())
                        .setIssuer("sapphire-messanger-server")
                        .setExpiration(this.expirationDate)
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


   

    public void setSigningKey(String key){
        this.singature = key;

    }


}
