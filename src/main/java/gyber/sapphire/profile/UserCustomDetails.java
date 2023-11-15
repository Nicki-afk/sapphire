package gyber.sapphire.profile;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCustomDetails implements UserDetails{

    
   private Long id;
   private String username;
   private String email;
   private String passwd;



    public UserCustomDetails(User usr){
        this.id = usr.getId();
        this.username = usr.getUserName();
        this.passwd = usr.getPasswd();
        this.email = usr.getEmail();
    }

    public UserCustomDetails(){}

    public UserCustomDetails(Long idUser , String username , String email ,  String passwd){
        this.id = idUser;
        this.username = username;
        this.email = email;
        this.passwd = passwd;
    }


 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return passwd; // null
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
