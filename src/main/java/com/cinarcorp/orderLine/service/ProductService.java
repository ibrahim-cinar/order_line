package com.cinarcorp.orderLine.service;

import com.cinarcorp.orderLine.dto.ProductDto;
import com.cinarcorp.orderLine.dto.converter.ProductDtoConverter;
import com.cinarcorp.orderLine.exception.UserNotFoundException;
import com.cinarcorp.orderLine.model.Order;
import com.cinarcorp.orderLine.model.Product;
import com.cinarcorp.orderLine.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDtoConverter productDtoConverter;
    public ProductService(ProductRepository productRepository, ProductDtoConverter productDtoConverter) {
        this.productRepository = productRepository;
        this.productDtoConverter = productDtoConverter;
    }

    protected Product findProductById(String id){
        return productRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Product could not find by id: " + id));

    }
    public ProductDto getProductById(String id){
        return productDtoConverter.convert(findProductById(id));
    }

    public List<ProductDto> findProductsBiggerThanPrice(int price) {

            return productRepository.getProductsBiggerThanPrice(price).stream()
                    .map(productDtoConverter::convert).collect(Collectors.toList());
    }
    public List<ProductDto>findProductsByProductName(String productName){
        return productRepository.findProductsByProductName(productName).stream()
                .map(productDtoConverter::convert).collect(Collectors.toList());
    }

    public ProductDto saveProductWithoutOrder(ProductDto productDto){
            // İlişkili bir Order nesnesi olmadan yeni bir Product nesnesi oluşturun
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        Order order = new Order();
        product.setOrder(order);

            // Product nesnesini kaydedin
           return productDtoConverter.convert(productRepository.save(product));


    }
    public void deleteProduct(String id) {

        var product = productRepository.getProductById(id);
            productRepository.deleteById(product.getId());
    }

}
