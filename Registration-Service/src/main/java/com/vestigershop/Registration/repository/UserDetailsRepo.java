package com.vestigershop.Registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vestigershop.Registration.Data.User;

@Repository
public interface UserDetailsRepo extends JpaRepository<User,Long> {

	Optional<User> findByUserName(String userName);

	//Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
