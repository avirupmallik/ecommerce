package com.vestigershop.CartService.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product{


	private int productId;
	
	private int categoryId;
	
	private String productName;
	
	private String title;
	
	private String description;
	
	private float price;
	
	private Integer quantity;

	private byte[] image;
}
