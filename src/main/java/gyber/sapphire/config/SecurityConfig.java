package gyber.sapphire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

      @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        

        // new AntPathRequestMatcher("/pub/**");
        // http
        //   .authorizeHttpRequests(authorize -> authorize
        //   .requestMatchers(AntPathRequestMatcher
        //   .antMatcher(HttpMethod.POST , "/proccess/login")
        //   .antMatcher("/simple")
        //   )
        //   .permitAll()
        //   .anyRequest().authenticated())
        //   .formLogin(login -> 
        //     login
        //   .loginPage("/login")
        //   .loginProcessingUrl("/proccess/login")
        //   .successForwardUrl("/simple")
        //   .permitAll()
          
        //   ).csrf(csrf -> csrf.disable());


        http
        .csrf(csrf -> csrf.disable())
        .authorizeRequests(auth -> {
          try {
            auth
            .requestMatchers(new AntPathRequestMatcher("/pub/**"))
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .loginProcessingUrl("/proccess/login")
            .successForwardUrl("/simple");
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        })
        .formLogin(formLogin -> formLogin
        .loginPage("/login")
        .permitAll()
        .loginProcessingUrl("/proccess/login"));



        /*
         * antMatcher() method is missing in newer versions of Spring
        */

        // http
        // .csrf()
        // .disable()
        // .authorizeRequests()
        // .antMatchers("/pub/**")
        // .permitAll()
        // .and()
        // .formLogin()
        // .loginPage("/login")
        // .loginProcessingUrl("/proccess/login")
        // .defaultSuccessUrl("/simple")
        // .and()
        // .logout()
        // .permitAll();



      
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }


    @Bean
    public UserDetailsService userDetailsService(){
      UserDetails service = User.withDefaultPasswordEncoder()
                                    .username("admin")
                                    .password("password")
                                    .build();

      return new InMemoryUserDetailsManager(service);

    }


    
}
