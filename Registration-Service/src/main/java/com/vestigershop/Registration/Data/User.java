package com.vestigershop.Registration.Data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"email"}))
@AllArgsConstructor
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
	private  Long userId;
	
	//@NotBlank
	@JsonProperty("first_name")
	private String firstName;
	
	//@NotBlank
	@JsonProperty("last_name")
	private String lastName;
	
	@NotBlank
	@Email
	private String email;
	
	@JsonProperty("phone_number")
	//@NotBlank
	private String phnNo;
	
	private String password;
	
	String userName;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Roles> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String email, String password,String firstName,String lastName,String phnNo) {
		this.userName = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName=lastName;
		this.phnNo = phnNo;
	}
	

}
