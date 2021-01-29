package com.vestigershop.CartService.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Cart")
@Data
public class CartDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;

	@Column(name = "ProductId")
	private Integer productId;

	@Column(name = "UserId")
	private Integer userId;

	@Column(name = "Quantity")
	private int quantity;

	@Column(name = "existFlag")
	private boolean isPresentInCart;
	
	@Column(name="TotalAmount")
	private Integer totalPrice;
}
