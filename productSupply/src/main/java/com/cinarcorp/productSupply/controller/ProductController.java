package com.cinarcorp.productSupply.controller;

import com.cinarcorp.productSupply.dto.ProductDto;
import com.cinarcorp.productSupply.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PostMapping
    public ResponseEntity<ProductDto> saveProductWithoutOrder(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.saveProductWithoutOrder(productDto));
    }

}
