package com.vestigershop.CartService.conversion;

import java.util.Optional;

import com.vestigershop.CartService.DO.CartDO;
import com.vestigershop.CartService.dto.CartDTO;

public final class CartObjectConversion {

	private CartObjectConversion() {
		
	}
	public static CartDO convertDtoToDo(CartDTO cartDto) {
		CartDO cartDO = new CartDO();
		if(cartDto.getCartId()!=null) {
			cartDO.setCartId(cartDto.getCartId());
		}
		cartDO.setProductId(cartDto.getProductId());
		cartDO.setPresentInCart(cartDto.isPresentInCart());
		cartDO.setQuantity(cartDto.getQuantity());
		cartDO.setUserId(cartDto.getUserId());
		cartDO.setTotalPrice(cartDto.getTotalPrice());
		return cartDO;
	}
	
	public static CartDTO convertDoToDto(CartDO cartDo) {
		CartDTO cartDto = new CartDTO();
		cartDto.setCartId(cartDo.getCartId());
		cartDto.setProductId(cartDo.getProductId());
		cartDto.setPresentInCart(cartDo.isPresentInCart());
		cartDto.setQuantity(cartDo.getQuantity());
		cartDto.setUserId(cartDo.getUserId());
		cartDto.setTotalPrice(cartDo.getTotalPrice());
		return cartDto;
	}
}
