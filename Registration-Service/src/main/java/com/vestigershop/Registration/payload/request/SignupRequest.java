package com.vestigershop.Registration.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SignupRequest {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	private Set<String> role;
	
	@JsonProperty("phone_number")
	private String phnNo;
	
	@JsonProperty("first_name")
	private String firstName;
	
	//@NotBlank
	@JsonProperty("last_name")
	private String lastName;
	

}
