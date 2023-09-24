package com.cinarcorp.productSupply.controller;

import com.cinarcorp.productSupply.dto.OrderDto;
import com.cinarcorp.productSupply.dto.ProductDto;
import com.cinarcorp.productSupply.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/price/{price}")
    public ResponseEntity<List<ProductDto>> findProductsBiggerThanPrice(@PathVariable int price){
        return ResponseEntity.ok(productService.findProductsBiggerThanPrice(price));
    }
    @GetMapping("/productName/{productName}")
    public ResponseEntity<List<ProductDto>> findProductsByProductName(@PathVariable String productName){
        return ResponseEntity.ok(productService.findProductsByProductName(productName));
    }
    @PostMapping
    public ResponseEntity<ProductDto> saveProductWithoutOrder(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.saveProductWithoutOrder(productDto));
    }

}
