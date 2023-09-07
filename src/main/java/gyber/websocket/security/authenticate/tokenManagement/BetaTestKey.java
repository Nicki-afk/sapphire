package gyber.websocket.security.authenticate.tokenManagement;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import gyber.websocket.models.User;
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
    @Column(name = "time_to_create")   private LocalDateTime timeToCreate;
    @Column(name = "expiration_date")  private LocalDateTime expirationDate;
    @Column(name = "key_is_active")    private boolean isActive;

    @Column(name = "beta_key")         private String key;

    // @OneToOne
    // private User user;


    
}
