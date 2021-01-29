package com.vestigershop.CartService.service;

import java.util.List;

import com.vestigershop.CartService.dto.CartDTO;

public interface CartService {

	List<CartDTO> getAllCartByUser(Integer userId);
	
	void saveCartByUser(CartDTO cart);
	
	 void updateCartByUser(CartDTO cart) ;
	 
	 List<CartDTO> getAllExistingCartByUser(Integer userId,boolean existFlag);
	 
	 List<CartDTO> getAllExistingCartByUserAndProductId(Integer userId,boolean existFlag,Integer productId);
}
