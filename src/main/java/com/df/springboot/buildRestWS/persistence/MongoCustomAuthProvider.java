package com.df.springboot.buildRestWS.persistence;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class MongoCustomAuthProvider implements AuthenticationProvider {
	
	@Autowired
	UserRepository repository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
        //String password = authentication.getCredentials().toString();
        
        com.df.springboot.buildRestWS.persistence.User user = repository.findByUsername(name);
        
        if (null != user) {
        	List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("greetingAPI"));
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), authorities);
        } else {
            return null;
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
