package com.vestigershop.ProductService.data;

import java.io.Serializable;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("productdetails")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7063939777897729535L;

	@Column("ProductId")
	private int productId;
	
	@Column("CategoryId")
	private int categoryId;
	
	@Column("ProductName")
	private String productName;
	
	@Column("ProductTitle")
	private String title;
	
	@Column("ProductDesc")
	private String description;
	
	
	private float price;
	
	@Column("AvailableQuantity")
	private Integer quantity;
	
	@Column("ProductImage")
	private byte[] image;
}
