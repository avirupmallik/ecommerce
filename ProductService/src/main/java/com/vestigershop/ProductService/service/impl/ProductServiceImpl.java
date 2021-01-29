package com.vestigershop.ProductService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vestigershop.ProductService.data.Product;
import com.vestigershop.ProductService.exception.ProductException;
import com.vestigershop.ProductService.repository.ProductRepository;
import com.vestigershop.ProductService.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Flux<Product> getProductByProductCategoryId(String name) {
		
		Mono<Integer> categoryId = productRepository.findProductCategory(name);
		categoryId.subscribe(null, (x)->{
			throw new ProductException("No categoryid found for " + x);
		});
		return	categoryId.flatMapMany(x->productRepository.findByProductCategoryId(x));
	}

	@Override
	public Mono<Product> getProductByProductName(String name) {
		return	productRepository.findByProductName(name);
	}

	@Override
	public Mono<Product> getProductByProductId(Integer id) {
		
		return productRepository.getProductById(id);
	}

}

