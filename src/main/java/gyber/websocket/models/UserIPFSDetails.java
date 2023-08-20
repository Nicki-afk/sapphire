package gyber.websocket.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserIPFSDetails implements UserDetails{

    private UserIPFSModel userIPFSModel;

    public UserIPFSDetails(UserIPFSModel userIPFSModel){
        this.userIPFSModel = userIPFSModel;
    }

    public UserIPFSDetails(){}


    public String getCryptoWlletAddress(){
        return this.userIPFSModel.getCryptoWalletAddress();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.userIPFSModel.getCryptoWalletAddress();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.userIPFSModel.getUserName();
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
