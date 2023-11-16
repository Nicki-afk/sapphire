package gyber.sapphire.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import gyber.sapphire.authentication.filters.JwtFilter;
import gyber.sapphire.authentication.filters.KeyFilter;
import gyber.sapphire.authentication.filters.RefreshFilter;
import gyber.sapphire.profile.UserCustomDetailsService;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfigClass {

    // @Autowired
    // private UserCustomDetailsService userIPFSCustomDetailsService;









    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/blog")).permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                // .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic();

        return http.build();
    }


@Bean
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}




    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                               .username("user")
                               .password("password")
                               .build();

        return new InMemoryUserDetailsManager(user);
    }

    // @Bean
	// public UserDetailsService userDetailsService() {
	// 	UserDetails user =
	// 		 User.withDefaultPasswordEncoder()
	// 			.username("user")
	// 			.password("password")
	// 			.roles("USER")
	// 			.build();

	// 	return new InMemoryUserDetailsManager(user);
	// }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userIPFSCustomDetailsService);

    // }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    
    //     http
    //     .addFilterBefore(getKeyFilter(), UsernamePasswordAuthenticationFilter.class)
    //     .addFilterBefore(getRefreshFilter() , UsernamePasswordAuthenticationFilter.class)
    //     .addFilterBefore(getJwtFilter(), UsernamePasswordAuthenticationFilter.class)
    //     .authorizeRequests()
    //     .antMatchers("/pub/**")
    //     .permitAll()
    //     .antMatchers("/post/**")
    //     .permitAll()
    //     .antMatchers("/h2-console/**")
    //     .permitAll()
    //     .antMatchers("/auth/**")
    //     .permitAll()
    //     .antMatchers("/system/**")
    //     .permitAll()
    //     .anyRequest().authenticated()
    //     .and()
    //     .formLogin()
    //     .and()
    //     .logout()
    //     .and()
    //     .csrf().disable();
    // }


    // @Bean
    // public JwtFilter getJwtFilter(){
    //     return new JwtFilter();

    // }


    // @Bean
    // public RefreshFilter getRefreshFilter(){
    //     return new RefreshFilter();
    // }

    // @Bean 
    // public KeyFilter getKeyFilter(){
    //     return new KeyFilter();
    // }




    







}
