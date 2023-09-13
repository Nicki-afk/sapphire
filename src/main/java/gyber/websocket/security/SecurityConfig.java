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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import gyber.websocket.models.UserCustomDetailsService;
import gyber.websocket.security.authenticate.filters.JwtFilter;
import gyber.websocket.security.authenticate.filters.KeyFilter;
import gyber.websocket.security.authenticate.filters.RefreshFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserCustomDetailsService userIPFSCustomDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userIPFSCustomDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
        http
        .addFilterBefore(getKeyFilter(), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(getRefreshFilter() , UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(getJwtFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers("/pub/**")
        .permitAll()
        .antMatchers("/register/**")
        .permitAll()
        .antMatchers("/h2-console/**")
        .permitAll()
        .antMatchers("/auth/**")
        .permitAll()
        .antMatchers("/system/**")
        .permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .logout()
        .and()
        .csrf().disable();
    }


    @Bean
    public JwtFilter getJwtFilter(){
        return new JwtFilter();

    }


    @Bean
    public RefreshFilter getRefreshFilter(){
        return new RefreshFilter();
    }

    @Bean 
    public KeyFilter getKeyFilter(){
        return new KeyFilter();
    }




    







}
