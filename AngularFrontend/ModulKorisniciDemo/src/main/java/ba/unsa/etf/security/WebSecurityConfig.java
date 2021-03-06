package ba.unsa.etf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable().authorizeRequests()
	        .antMatchers("/").permitAll()
	    .antMatchers(HttpMethod.POST, "/login").permitAll()
	    .antMatchers(HttpMethod.POST, "/korisnik/resetPassword").permitAll()
	    .antMatchers(HttpMethod.GET, "/korisnik/promjenaPassworda").permitAll()
	    .antMatchers("/korisnik/updatePassword",
                     "/korisnik/sacuvajPassword",
                     "/updatePassword")
	    .hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
	    .anyRequest().authenticated()
	    .and()
	    // We filter the api/login requests
	    .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	            UsernamePasswordAuthenticationFilter.class)
	    // And filter other requests to check the presence of JWT in header
	        .addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
	  }
	
	  /*@Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    // Create a default account
		  auth.inMemoryAuthentication()
	    .withUser("admin")
	    .password("password")
	    .roles("ADMIN");
	    
	  }*/
	  
	  @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	       auth.userDetailsService(userDetailsService);
	  }
}
