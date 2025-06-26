package com.video.dto;

import com.video.model.User;

/**
 * 
 * @author Aneswara reddy
 *
 */
public class AuthResponse {

	private User user;
	private String token;
	
	
	public AuthResponse(User user, String token) {
		super();
		this.user = user;
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
