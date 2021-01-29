package com.vestigershop.CartService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.inject.spi.Message;
import com.vestigershop.CartService.dto.CartDTO;
import com.vestigershop.CartService.dto.MessageResponse;
import com.vestigershop.CartService.service.impl.CartServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class CartController {

	private CartServiceImpl cartServiceImpl;
	
	public CartController(CartServiceImpl cartServiceImpl) {
		
		this.cartServiceImpl = cartServiceImpl;
	}
	
	@GetMapping("/carts/{userId}/{productId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<CartDTO>> findCartDetailsByUserAndProductId(@PathVariable Integer userId,@Nullable @PathVariable Integer productId){
		List<CartDTO> cartList = cartServiceImpl.getAllExistingCartByUserAndProductId(userId,true,productId);
		return ResponseEntity.ok(cartList);
		
	}
	
	@GetMapping("/carts/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<CartDTO>> findCartDetailsByUser(@PathVariable Integer userId){
		List<CartDTO> cartList = cartServiceImpl.getAllExistingCartByUser(userId,true);
		return ResponseEntity.ok(cartList);
		
	}
	
	@PostMapping("/carts/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> saveCartDetailsByUser(@RequestBody CartDTO cart, @PathVariable Integer userId){
		cartServiceImpl.saveCartByUser(cart);
		return ResponseEntity.ok(new MessageResponse("Cart item has been added for the user "+userId));
		
	}
	
	@PutMapping("/carts/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateCartDetailsByUser(@RequestBody CartDTO cart, @PathVariable Integer userId){
		cartServiceImpl.updateCartByUser(cart);
		return ResponseEntity.ok(new MessageResponse("Cart item has been added for the user "+userId));
		
	}
}
