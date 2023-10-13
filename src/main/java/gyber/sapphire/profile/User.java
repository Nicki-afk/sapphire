package gyber.sapphire.profile;

import java.util.Set;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import gyber.sapphire.beta.BetaTestKey;
import gyber.sapphire.messaging.Chat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode
@ToString
/*
 * TODO :  Добавить черный список для пользователей 
 *         и написать соответвующие запросы 
 */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username") @Size(min = 6 , max = 8) @NotBlank  private String userName;

    @Column(name = "crypto_wallet") @NotBlank  private String cryptoWalletAddress;
    @Column(name = "pub_key") @NotBlank private String publicUserKey;

    @Enumerated(EnumType.STRING) @Column(name = "net_status")  private NetStatus onlineNetStatus;

    @Column(name = "hash_data")
    @NotBlank 
    private String hashUserFile;

   
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beta_test_key", referencedColumnName = "id")
    private BetaTestKey betaTestKey;


    @OneToMany(mappedBy = "userChats" )
    private Set<Chat>userChats;
    

    public User(Long id, String userName, String cryptoWalletAddress, String publicUserKey, NetStatus onlineNetStatus,
            String hashUserFile) {
        this.id = id;
        this.userName = userName;
        this.cryptoWalletAddress = cryptoWalletAddress;
        this.publicUserKey = publicUserKey;
        this.onlineNetStatus = onlineNetStatus;
        this.hashUserFile = hashUserFile;
    }

    


    

}
