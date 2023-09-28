package gyber.sapphire.entities;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gyber.sapphire.entities.repositories.UserRepository;

@Service
public class UserCustomDetailsService  implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    // String username change to signature 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        gyber.sapphire.entities.User user = null;
        if(username.length() > 10){
            user = this.userRepository.findByCryptoWalletAddress(username).orElseThrow(() -> new UsernameNotFoundException("The cryptowalletaddress not found"));

        }else{
            user = this.userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("The username not found "));
        }


        return new User(
            user.getUserName() , 
            "" , 
            new ArrayList<>()

        );
    }

    public UserCustomDetails loadUserByCryptowalletAddress(String cryptoWalletAddress){
        UserCustomDetails userIPFSDetails = new UserCustomDetails(this.userRepository.findByCryptoWalletAddress(cryptoWalletAddress).orElseThrow(() -> new UsernameNotFoundException("Cryptowallet address not found")));
        return userIPFSDetails;

    }
    
}
