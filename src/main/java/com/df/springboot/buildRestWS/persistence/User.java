package com.df.springboot.buildRestWS.persistence;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class User {
	
	@Id
	private ObjectId _id;
	
	private String username;
	private String password;
	private List<SimpleGrantedAuthority> authorities;
	
	public User() {
		
	}

	public User(ObjectId _id, String username, String password, List<SimpleGrantedAuthority> authorities) {
		super();
		this._id = _id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<SimpleGrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	

}
