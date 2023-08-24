package gyber.websocket.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserIPFSDetails implements UserDetails{

   // private UserIPFSModel userIPFSModel;

   private Long id;
   private String username;
   private String cryptoWalletAddress;


    public UserIPFSDetails(UserIPFSModel userIPFSModel){
        this.id = userIPFSModel.getId();
        this.cryptoWalletAddress = userIPFSModel.getCryptoWalletAddress();
        this.username = userIPFSModel.getUserName();
    }

    public UserIPFSDetails(){}

    public UserIPFSDetails(Long idUser , String username , String cryptoWalletAddress){
        this.id = idUser;
        this.username = username;
        this.cryptoWalletAddress = cryptoWalletAddress;
    }


    public String getCryptoWlletAddress(){
        return this.cryptoWalletAddress;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return null; // null
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
