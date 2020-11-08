package com.vestigershop.ProductService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vestigershop.ProductService.data.Product;
import com.vestigershop.ProductService.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

	@Autowired
	ProductService product;
	
	    @GetMapping("{name}")
	    public Flux<Product> get(@PathVariable("name") String name) {
	        return this.product.getProductByProductCategoryId(name);
	    }
	    
	    @GetMapping("/product/{name}")
	    public Mono<Product> getProductByName(@PathVariable("name") String name) {
	        return this.product.getProductByProductName(name);
	    }
}
