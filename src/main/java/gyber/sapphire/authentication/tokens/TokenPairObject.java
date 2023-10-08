package gyber.sapphire.authentication.tokens;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming
public class TokenPairObject {

    @JsonProperty(value = "time_to_create")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private  final LocalDateTime timeToCreateTokenPair = LocalDateTime.now();
    @JsonProperty(value = "jwt")
    private String jwtToken;
    @JsonProperty(value = "refresh_token")
    private String refreshToken;






    
}
