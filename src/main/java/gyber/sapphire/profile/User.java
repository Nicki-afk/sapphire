package gyber.sapphire.profile;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import gyber.sapphire.beta.BetaTestKey;
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
 * @nic_ko : Поля Chats / Messages хрантся в IPFS
 * 
 * - Добавить поле для хранения ссылок чатов в IPFS
 */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username" , unique = true)
    @Size(min = 6, max = 8)
    @NotBlank
    private String userName;

    @Column(name = "crypto_wallet" , unique = true)
    @NotBlank
    private String cryptoWalletAddress;
    
    @Column(name = "pub_key")
    @NotBlank
    private String publicUserKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "net_status")
    private NetStatus onlineNetStatus;

    @Column(name = "hash_data")
    @NotBlank
    private String hashUserFile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beta_test_key", referencedColumnName = "id")
    private BetaTestKey betaTestKey;

    public User(String userName, String cryptoWalletAddress, String publicUserKey, NetStatus onlineNetStatus,
            String hashUserFile) {
        this.userName = userName;
        this.cryptoWalletAddress = cryptoWalletAddress;
        this.publicUserKey = publicUserKey;
        this.onlineNetStatus = onlineNetStatus;
        this.hashUserFile = hashUserFile;
    }

    /*
     * For Tests
     */
    public User(Long id, @Size(min = 6, max = 8) @NotBlank String userName, @NotBlank String cryptoWalletAddress,
            @NotBlank String publicUserKey, NetStatus onlineNetStatus, @NotBlank String hashUserFile) {
        this.id = id;
        this.userName = userName;
        this.cryptoWalletAddress = cryptoWalletAddress;
        this.publicUserKey = publicUserKey;
        this.onlineNetStatus = onlineNetStatus;
        this.hashUserFile = hashUserFile;
    }

    /*
     * For Tests
     */
    public User(@Size(min = 6, max = 8) @NotBlank String userName, @NotBlank String cryptoWalletAddress,
            @NotBlank String publicUserKey, NetStatus onlineNetStatus, @NotBlank String hashUserFile,
            @NotNull BetaTestKey betaKey) {

        this.userName = userName;
        this.cryptoWalletAddress = cryptoWalletAddress;
        this.publicUserKey = publicUserKey;
        this.onlineNetStatus = onlineNetStatus;
        this.hashUserFile = hashUserFile;
        this.betaTestKey = betaKey;
    }

}
