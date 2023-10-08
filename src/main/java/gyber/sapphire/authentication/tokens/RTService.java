package gyber.sapphire.authentication.tokens;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import net.bytebuddy.utility.RandomString;

@Service
public class RTService implements TokenAuthenticate{



    private String randomTokenString =  new RandomString(128).nextString();
    private Date expirDate;


    @Override
    public String createToken() {

        this.expirDate = Date.from(LocalDateTime.now().plusDays(2).atZone(ZoneId.systemDefault()).toInstant());
     
       String refreshTokenString =  new StringBuilder()
                                        .append(randomTokenString)
                                        .append("_")
                                        .append(LocalDateTime.now())
                                        .append("_")
                                        .append(this.expirDate.getTime())
                                        .toString();

        String refreshTokenBase64 = Base64.getEncoder().encodeToString(refreshTokenString.getBytes());

        return refreshTokenBase64;
    }


    // for test case
    public String testCreateToken(long seconds){
        this.expirDate = Date.from(LocalDateTime.now().plusSeconds(seconds).atZone(ZoneId.systemDefault()).toInstant());
     
       String refreshTokenString =  new StringBuilder()
                                        .append(randomTokenString)
                                        .append("_")
                                        .append(LocalDateTime.now())
                                        .append("_")
                                        .append(this.expirDate.getTime())
                                        .toString();

        String refreshTokenBase64 = Base64.getEncoder().encodeToString(refreshTokenString.getBytes());

        return refreshTokenBase64;


    }

    @Override
    public boolean validateToken(String token) {

        if(token == null || token.isEmpty() ){
            throw new NullPointerException("Token is null or empty");

        }

        String refreshTokenDecode = new String(Base64.getDecoder().decode(token));


        LocalDateTime timeValidRefreshToken = Instant.ofEpochMilli(  ( Long.parseLong(refreshTokenDecode.split("_")[2])  )  ).atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDateTime currentTime = LocalDateTime.now();

        if(timeValidRefreshToken.isBefore(currentTime)){

            return false;

        }

        return true;
    
    }

    // public RTService setExpirationDate(long seconds){
    //     this.expirDate = Date.from(LocalDateTime.now().plusSeconds(seconds).atZone(ZoneId.systemDefault()).toInstant());

    //     return this;

    // }

    public RTService setSecretRandomString(String randomString){
        this.randomTokenString = randomString;
        return this;
    }
    
}
