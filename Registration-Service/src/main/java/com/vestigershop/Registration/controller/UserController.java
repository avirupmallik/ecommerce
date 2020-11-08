//package com.vestigershop.Registration.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.vestigershop.Registration.Data.User;
//import com.vestigershop.Registration.service.UserDetailsServiceCustom;
//
//@RestController("/api/v1")
//@CrossOrigin(origins = "*",allowedHeaders = "*")
//public class UserController {
//
//	@Autowired
//	private UserDetailsServiceCustom detailsService;
//	
//	@GetMapping("/users")
//	public ResponseEntity<List<User>> getAllUser(){
//		
//		List<User> allUser = detailsService.getAllUser();
//		
//		return ResponseEntity.ok(allUser);
//	}
//	
//	@PostMapping("/user")
//	public ResponseEntity createUser(@RequestBody User details){
//		
//		 detailsService.createUser(details);
//		
//		return ResponseEntity.ok().build();
//	}
//}
