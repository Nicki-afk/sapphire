package gyber.sapphire.profile;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gyber.sapphire.database.repositories.UserRepository;

@Service
public class UserCustomDetailsService  implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    // String username change to signature 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

         gyber.sapphire.profile.User user = this.userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("The username not found "));


        return new User(
            user.getUserName() , 
            user.getPasswd() , 
            new ArrayList<>()

        );


    }

 
    
}
