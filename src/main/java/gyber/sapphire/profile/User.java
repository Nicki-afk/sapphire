package gyber.sapphire.profile;

import java.time.LocalDateTime;
import java.util.List;
import gyber.sapphire.messaging.Chat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
 Fields userAvatar , blockedUsers , notifications which 
 most be defined in ENTITIES document 
 was delayed for quick start and MVP testing
 */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username" , unique = true)
    @Size(min = 6, max = 8)
    @NotBlank
    private String userName;


    @Column(name = "email" , unique = true)
    @Email @NotBlank
    private String email;

    @Column(name = "passwd")
    @Size(min = 8) @NotBlank
    private String passwd;

    @Column(name = "creat_at")
    @NotBlank
    private LocalDateTime createAt;


    @Enumerated(EnumType.STRING)
    @Column(name = "net_status")
    private NetStatus onlineNetStatus;


    @OneToMany(mappedBy = "usr")
    private List<Chat>chats;

    @Column(name = "user_lang")
    @NotBlank
    private String lang;


     /*
     * beta test logic temporary disabled in 
     * MVP period
     */
    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "beta_test_key", referencedColumnName = "id")
    // private BetaTestKey betaTestKey;




    public User(@Size(min = 6, max = 8) @NotBlank String userName, @Email @NotBlank String email,
            @Size(min = 8) @NotBlank String passwd) {
        this.userName = userName;
        this.email = email;
        this.passwd = passwd;
    }
 


}
