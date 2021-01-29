package com.vestigershop.CartService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.vestigershop.CartService.security.data.User;



@Repository
@Component
public interface UserDetailsRepo extends JpaRepository<User,Long> {

	Optional<User> findByUserName(String userName);

	//Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
