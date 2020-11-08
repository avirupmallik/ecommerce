package com.vestigershop.Registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vestigershop.Registration.Data.Roles;
import com.vestigershop.Registration.utils.UserRoles;

public interface RolesRepo extends JpaRepository<Roles, Integer> {
	
	Optional<Roles> findByRoleName(UserRoles name);


}
