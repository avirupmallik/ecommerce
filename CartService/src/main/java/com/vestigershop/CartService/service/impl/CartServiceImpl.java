package com.vestigershop.CartService.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vestigershop.CartService.DO.CartDO;
import com.vestigershop.CartService.conversion.CartObjectConversion;
import com.vestigershop.CartService.dto.CartDTO;
import com.vestigershop.CartService.dto.Product;
import com.vestigershop.CartService.repository.CartRepository;
import com.vestigershop.CartService.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepo;

	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepo = cartRepository;
	}

	@Override
	public List<CartDTO> getAllCartByUser(Integer userId) {

		List<CartDTO> newCarts = new ArrayList<>();
		cartRepo.findAllCartItemByUserId(userId).forEach(x -> {
			WebClient client = WebClient.create("http://localhost:8082/api/products");
			ResponseEntity<Product> result = client.get().uri("/pid/{id}", x.getProductId())
					.accept(MediaType.APPLICATION_JSON).retrieve().toEntity(Product.class).block();
			CartDTO cartDTO = CartObjectConversion.convertDoToDto(x);
			cartDTO.setPrice(result.getBody().getPrice());
			cartDTO.setProductName(result.getBody().getProductName());
			cartDTO.setProductDesc(result.getBody().getDescription());
			newCarts.add(cartDTO);
		});

		return newCarts;
	}

	@Override
	public void saveCartByUser(CartDTO cart) {
        cartRepo.save(CartObjectConversion.convertDtoToDo(cart));
	}

	public void updateCartByUser(CartDTO cart) {
		CartDO cartDO = CartObjectConversion.convertDtoToDo(cart);
		  if(cartDO.getCartId()!=null) {
			  cartRepo.findById(cartDO.getCartId());
		  }
		  cartRepo.save(cartDO);
	  
	}

	@Override
	public List<CartDTO> getAllExistingCartByUserAndProductId(Integer userId, boolean existFlag,Integer productId) {
		List<CartDTO> newCarts = new ArrayList<>();
		cartRepo.findByUserIdAndIsPresentInCartAndProductId(userId,existFlag,productId).forEach(x -> {
			WebClient client = WebClient.create("http://localhost:8082/api/products");
			ResponseEntity<Product> result = client.get().uri("/pid/{id}", x.getProductId())
					.accept(MediaType.APPLICATION_JSON).retrieve().toEntity(Product.class).block();
			CartDTO cartDTO = CartObjectConversion.convertDoToDto(x);
			cartDTO.setPrice(result.getBody().getPrice());
			cartDTO.setProductName(result.getBody().getProductName());
			cartDTO.setProductDesc(result.getBody().getDescription());
			newCarts.add(cartDTO);
		});
		return newCarts;
	}
	@Override
	public List<CartDTO> getAllExistingCartByUser(Integer userId, boolean existFlag) {
		List<CartDTO> newCarts = new ArrayList<>();
		cartRepo.findByUserIdAndIsPresentInCart(userId,existFlag).forEach(x -> {
			WebClient client = WebClient.create("http://localhost:8082/api/products");
			ResponseEntity<Product> result = client.get().uri("/pid/{id}", x.getProductId())
					.accept(MediaType.APPLICATION_JSON).retrieve().toEntity(Product.class).block();
			CartDTO cartDTO = CartObjectConversion.convertDoToDto(x);
			cartDTO.setPrice(result.getBody().getPrice());
			cartDTO.setProductName(result.getBody().getProductName());
			cartDTO.setProductDesc(result.getBody().getDescription());
			newCarts.add(cartDTO);
		});
		return newCarts;
	}
}