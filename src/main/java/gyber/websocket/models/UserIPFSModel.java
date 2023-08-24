package gyber.websocket.models;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class UserIPFSModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")         private String userName;
    @Column(name = "crypto_wallet")    private String cryptoWalletAddress;
    @Column(name = "pub_key")          private String publicUserKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "net_status")
    private NetStatus onlineNetStatus;
    
    @Column(name = "hash_data") private String hashUserFile;


  //  @JsonIgnore
    @Transient
    private User user;

    public UserIPFSModel(User user){
        this.user = user;
        this.id = user.getId();
        this.userName = user.getUserName();
        this.cryptoWalletAddress = user.getCryptoWallet();
        this.onlineNetStatus = user.getOnlineStatus();

    }


}
