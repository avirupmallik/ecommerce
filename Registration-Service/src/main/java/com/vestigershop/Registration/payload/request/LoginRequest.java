package com.vestigershop.Registration.payload.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class LoginRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6774921064080942956L;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

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
}