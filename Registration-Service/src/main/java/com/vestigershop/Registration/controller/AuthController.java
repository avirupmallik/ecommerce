package com.vestigershop.Registration.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vestigershop.Registration.Data.Roles;
import com.vestigershop.Registration.Data.User;
import com.vestigershop.Registration.payload.request.LoginRequest;
import com.vestigershop.Registration.payload.request.SignupRequest;
import com.vestigershop.Registration.payload.response.JwtResponse;
import com.vestigershop.Registration.payload.response.MessageResponse;
import com.vestigershop.Registration.repository.RolesRepo;
import com.vestigershop.Registration.repository.UserDetailsRepo;
import com.vestigershop.Registration.security.jwt.JwtUtils;
import com.vestigershop.Registration.service.impl.UserDetailsImpl;
import com.vestigershop.Registration.utils.UserRoles;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsRepo userRepository;

	@Autowired
	RolesRepo roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		String jwt = jwtUtils.generateJwtToken(authentication);
		

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstName(), signUpRequest.getLastName(),
				signUpRequest.getPhnNo());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Roles> roles = new HashSet<>();

		if (strRoles == null) {
			Roles userRole = roleRepository.findByRoleName(UserRoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);

		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Roles adminRole = roleRepository.findByRoleName(UserRoles.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Roles modRole = roleRepository.findByRoleName(UserRoles.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Roles userRole = roleRepository.findByRoleName(UserRoles.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
