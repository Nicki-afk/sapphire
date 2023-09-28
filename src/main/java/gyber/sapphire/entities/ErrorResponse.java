package gyber.sapphire.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    
    private String formatErrorTime;
    private int errorCode;
    private String  exceptionDescription;
    private String message;


 
    public ErrorResponse(){}

    public ErrorResponse(LocalDateTime localDateTime , int errorCode , Exception exception , String message){
        this.formatErrorTime = localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss-dd:MM:yyyy"));
        this.errorCode = errorCode;
        this.exceptionDescription = exception.getMessage();
        this.message = message;

    }



    
}
