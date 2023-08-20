package gyber.websocket.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import gyber.websocket.models.repo.UserRepository;

public class UserIPFSCustomDetailsService  implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserIPFSModel userIPFSModel = null;
        if(username.length() > 10){
            userIPFSModel = this.userRepository.findByCryptoWalletAddress(username);

        }else{
            userIPFSModel = this.userRepository.findByUsername(username);
        }


        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }
    
}
