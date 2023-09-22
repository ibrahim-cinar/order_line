package com.cinarcorp.productSupply.dto.converter;

import com.cinarcorp.productSupply.dto.ProductDto;
import com.cinarcorp.productSupply.model.Product;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component

public class ProductDtoConverter {

    public ProductDto convert(Product from) {
        return new ProductDto(from.getId(),
                from.getProductName(),
                from.getDescription(),
                from.getPrice());
    }
}
