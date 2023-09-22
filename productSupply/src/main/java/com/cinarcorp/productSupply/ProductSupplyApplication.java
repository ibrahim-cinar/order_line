package com.cinarcorp.productSupply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cinarcorp.productSupply")
public class ProductSupplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductSupplyApplication.class, args);
	}

}
