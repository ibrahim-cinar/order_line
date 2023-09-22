package com.cinarcorp.productSupply.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private   ProductRepository productRepository;


    @ParameterizedTest
    @ValueSource(strings = {"d5b5aaec-0686-4074-88bb-a6d6e49dd809"})
    public void getProductById(String id) {
        var product = productRepository.getProductById(id);
        System.out.println(product.getProductName());
    }
}