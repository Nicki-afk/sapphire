package gyber.websocket.models;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gyber.websocket.models.repo.UserRepository;

@Service
public class UserIPFSCustomDetailsService  implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    // String username change to signature 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserIPFSModel userIPFSModel = null;
        if(username.length() > 10){
            userIPFSModel = this.userRepository.findByCryptoWalletAddress(username).orElseThrow(() -> new UsernameNotFoundException("The cryptowalletaddress not found"));

        }else{
            userIPFSModel = this.userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("The username not found "));
        }


        return new User(
            userIPFSModel.getUserName() , 
            "" , 
            new ArrayList<>()

        );
    }

    public UserIPFSDetails loadUserByCryptowalletAddress(String cryptoWalletAddress){
        UserIPFSDetails userIPFSDetails = new UserIPFSDetails(this.userRepository.findByCryptoWalletAddress(cryptoWalletAddress).orElseThrow(() -> new UsernameNotFoundException("Cryptowallet address not found")));
        return userIPFSDetails;

    }
    
}
