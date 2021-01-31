package com.vestigershop.Registration.payload.request;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SignupRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7764946831151131515L;

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
