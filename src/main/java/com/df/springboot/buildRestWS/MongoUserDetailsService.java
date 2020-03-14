package com.df.springboot.buildRestWS;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.df.springboot.buildRestWS.persistence.UserRepository;

@Component
public class MongoUserDetailsService implements UserDetailsService{
	
  @Autowired
  private UserRepository repository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  com.df.springboot.buildRestWS.persistence.User user = repository.findByUsername(username);
    if(user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    //List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("greetingAPI"));
    return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
  }
  
  @Bean
  BCryptPasswordEncoder bCryptPasswordEncoder() {
	  return new BCryptPasswordEncoder();
  }
}
