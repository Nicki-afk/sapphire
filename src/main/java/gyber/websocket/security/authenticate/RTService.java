package gyber.websocket.security.authenticate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import gyber.websocket.models.UserIPFSDetails;
import gyber.websocket.models.UserIPFSModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.bytebuddy.utility.RandomString;

@Service
public class RTService implements TokenAuthenticate{


    @Value("${rt.token.sign}")
    private String tokenSecret;


    @Override
    public String createToken() {
        String randomString = new RandomString(128).nextString();
        StringBuilder stringBuilder = new StringBuilder();

       String refreshTokenString =  stringBuilder
                                    .append(randomString)
                                    .append("_")
                                    .append(LocalDateTime.now())
                                    .append("_")
                                    .append(Date.from(LocalDateTime.now().plusDays(2).atZone(ZoneId.systemDefault()).toInstant()).getTime())
                                    .toString();

        String refreshTokenBase64 = Base64.getEncoder().encodeToString(refreshTokenString.getBytes());

        return refreshTokenBase64;
    }

    @Override
    public boolean validateToken(String token) {

        String refreshTokenDecode = new String(Base64.getDecoder().decode(token));
        LocalDateTime timeValidRefreshToken = Instant.ofEpochMilli(  ( Long.parseLong(refreshTokenDecode.split("_")[2])  )  ).atZone(ZoneId.systemDefault()).toLocalDateTime();

        if(LocalDateTime.now().isAfter(timeValidRefreshToken) || LocalDateTime.now().isEqual(timeValidRefreshToken)){

            return false;

        }

        return true;
    
    }
    
}
