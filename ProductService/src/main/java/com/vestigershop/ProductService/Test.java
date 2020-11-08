package com.vestigershop.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Test {

	public static void main(String[] args) {
		//Flux.just(1,2,4,3).log().subscribe(System.out::println);
		Flux<Integer> ints = Flux.range(1, 4);
		ints.log().subscribe(System.out::println,
		    error -> System.err.println("Error " + error),
		    () -> System.out.println("Done"),
		    sub -> sub.request(2));
	}

}
