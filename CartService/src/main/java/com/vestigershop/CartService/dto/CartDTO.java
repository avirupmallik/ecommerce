package com.vestigershop.CartService.dto;

import lombok.Data;

@Data
public class CartDTO {
	
    
	private Integer cartId;
	
	private Integer productId;
	
	private String productName;
	
	private String productDesc;
	
	private Integer userId;
	
	private float price;
	
	private int quantity;
	
	private boolean isPresentInCart;
	
	private Integer totalPrice;

}
