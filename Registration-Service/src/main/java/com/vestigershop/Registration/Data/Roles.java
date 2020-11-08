package com.vestigershop.Registration.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.vestigershop.Registration.utils.UserRoles;

import lombok.Data;

@Entity
@Data
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private UserRoles roleName;
	
	public Roles() {

	}

	public Roles(UserRoles name) {
		this.roleName = name;
	}
	
}
