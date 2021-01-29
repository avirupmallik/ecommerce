package com.vestigershop.ProductService.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.vestigershop.ProductService.data.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

	@Query("Select CategoryId from productcategory where CategoryName = :cName")
	Mono<Integer> findProductCategory(String cName);
	
	@Query("Select ProductName,ProductId from productdetails where CategoryId = :cId")
	Flux<Product> findByProductCategoryId(Integer x);
	
	@Query("Select * from productdetails where ProductName = :pName")
	Mono<Product> findByProductName(String name);
	
	@Query("Select * from productdetails where ProductId = :pId")
	Mono<Product> getProductById(Integer id);
}
