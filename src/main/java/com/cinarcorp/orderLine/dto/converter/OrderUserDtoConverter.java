package com.cinarcorp.orderLine.dto.converter;
import com.cinarcorp.orderLine.dto.OrderUserDto;
import com.cinarcorp.orderLine.model.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderUserDtoConverter {
    private final ProductDtoConverter productDtoConverter;

    public OrderUserDtoConverter(ProductDtoConverter converter) {
        this.productDtoConverter = converter;
    }


    public OrderUserDto convert(Order from){
        return  new OrderUserDto(
                Objects.requireNonNull(from.getId()),
                from.getTotalPaid(),from.getPiece(),
                from.getOrderDate(),from.isComplete(),
                from.getProduct().stream().map(productDtoConverter::convert)
                        .collect(Collectors.toList()));
    }
}