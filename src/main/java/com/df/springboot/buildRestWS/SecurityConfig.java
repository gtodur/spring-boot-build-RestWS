package com.df.springboot.buildRestWS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.df.springboot.buildRestWS.persistence.MongoCustomAuthProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private static final String REALM = "WS_REALM";
	@Autowired
	private MongoUserDetailsService userDetailsService;
	
	@Autowired
	private MongoCustomAuthProvider mongoCustomAuthProvider;
	
	@Override
	public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
	 authBuilder.authenticationProvider(mongoCustomAuthProvider);
		authBuilder.userDetailsService(userDetailsService);
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(bCryptPasswordEncoder());
	    return authProvider;
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
  
      http.csrf().disable()
      .authorizeRequests()
      .antMatchers("/greeting").hasAuthority("greetingAPI")
      .antMatchers("/sayBye").hasAuthority("sayByeAPI")
      .antMatchers("/saySomethingElse").hasAuthority("saySomethingElseAPI")
      .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
      .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
    }
    
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
    
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
