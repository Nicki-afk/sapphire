package gyber.websocket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import gyber.websocket.models.UserIPFSCustomDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserIPFSCustomDetailsService userIPFSCustomDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userIPFSCustomDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
        http
        .authorizeRequests()
        .antMatchers("/pub/**")
        .permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .logout()
        .and()
        .csrf().disable();
    }


    







}
