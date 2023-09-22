package com.cinarcorp.productSupply.dto.converter;

import com.cinarcorp.productSupply.dto.OrderDto;
import com.cinarcorp.productSupply.model.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderDtoConverter {

        private final ProductDtoConverter productDtoConverter;
        private final UserDtoConverter userDtoConverter;


    public OrderDtoConverter(ProductDtoConverter productDtoConverter, UserDtoConverter userDtoConverter) {
        this.productDtoConverter = productDtoConverter;
        this.userDtoConverter = userDtoConverter;
    }

    public OrderDto convert(Order from){
        return  new OrderDto(
                Objects.requireNonNull(from.getId()),
                from.getTotalPaid(),from.getPiece(),
                from.getOrderDate(),from.isComplete(),
                userDtoConverter.convertToUserDto(from.getUser()),
                from.getProduct().stream().map(productDtoConverter::convert)
                .collect(Collectors.toList()));
    }
}


