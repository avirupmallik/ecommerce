package com.vestigershop.CartService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vestigershop.CartService.DO.CartDO;

@Repository
public interface CartRepository extends JpaRepository<CartDO,Integer> {
	
	List<CartDO> findAllCartItemByUserId(Integer userId);
	
	List<CartDO> findByUserIdAndIsPresentInCartAndProductId(Integer userId,boolean existFlag,Integer productId);
	
	List<CartDO> findByUserIdAndIsPresentInCart(Integer userId,boolean existFlag);
	

}
