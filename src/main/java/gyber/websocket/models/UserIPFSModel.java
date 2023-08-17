package gyber.websocket.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserIPFSModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")         private String userName;
    @Column(name = "crypto_wallet")    private String cryptoWalletAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "net_status")
    private NetStatus onlineNetStatus;
    
    @Column(name = "hash_data") private String hashUserFile;


    @JsonIgnore
    private User user;

    public UserIPFSModel(User user){
        this.user = user;
        this.id = user.getId();
        this.userName = user.getUserName();
        this.cryptoWalletAddress = user.getCryptoWallet();
        this.onlineNetStatus = user.getOnlineStatus();

    }


}
