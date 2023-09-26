package com.cinarcorp.productSupply;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cinarcorp.productSupply")
public class ProductSupplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductSupplyApplication.class, args);
	}

	@Bean
	public OpenAPI orderOpenAPI(@Value("${application-description}")String description
			,@Value("${application-version}")String version){
	return new OpenAPI().info(new Info().title("order line app")
			.version(version).description(description)
			.license(new License()
					.name("Cinar corp Licence")));
	}


}
