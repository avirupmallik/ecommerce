package com.vestigershop.ProductService.service;

import com.vestigershop.ProductService.data.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	Flux<Product> getProductByProductCategoryId(String name);
	Mono<Product> getProductByProductName(String name);
}
