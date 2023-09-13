package gyber.websocket.security.beta;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import gyber.websocket.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "users_keys")
@Entity
public class BetaTestKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "time_to_create")  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")   private LocalDateTime timeToCreate;
    @Column(name = "expiration_date")  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")  private LocalDateTime expirationDate;
    @Column(name = "key_is_active")    private boolean isActive;

    @Column(name = "beta_key")               private String key;
    @OneToOne(mappedBy = "betaTestKey")      private User user;

    public BetaTestKey(String key) {
        this.key = key;
        this.expirationDate = LocalDateTime.now().plusMonths(1);
        this.timeToCreate = LocalDateTime.now();
    }

    public BetaTestKey(LocalDateTime timeToCreate, LocalDateTime expirationDate, boolean isActive, String key) {
        this.timeToCreate = timeToCreate;
        this.expirationDate = expirationDate;
        this.isActive = isActive;
        this.key = key;
    }


    


    

    // @OneToOne
    // private User user;


    
}
