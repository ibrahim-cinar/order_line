package com.cinarcorp.orderLine.dto.converter;

import com.cinarcorp.orderLine.dto.ProductDto;
import com.cinarcorp.orderLine.model.Product;
import org.springframework.stereotype.Component;

@Component

public class ProductDtoConverter {

    public ProductDto convert(Product from) {
        return new ProductDto(from.getId(),
                from.getProductName(),
                from.getDescription(),
                from.getPrice());
    }
}
