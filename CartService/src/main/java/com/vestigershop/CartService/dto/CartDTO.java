package com.vestigershop.CartService.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartDTO implements Serializable {
	
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 234375502103601879L;

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
