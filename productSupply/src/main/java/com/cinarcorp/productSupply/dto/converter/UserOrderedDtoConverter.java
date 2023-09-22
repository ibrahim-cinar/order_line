package com.cinarcorp.productSupply.dto.converter;

import com.cinarcorp.productSupply.dto.UserOrderedDto;
import com.cinarcorp.productSupply.model.Order;
import com.cinarcorp.productSupply.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserOrderedDtoConverter {
    private final OrderUserDtoConverter orderUserDtoConverter;
    public UserOrderedDtoConverter(OrderUserDtoConverter orderUserDtoConverter) {
        this.orderUserDtoConverter = orderUserDtoConverter;
    }
    public UserOrderedDto convert(User from){
        return new UserOrderedDto(from.getFirstName(),
                from.getLastName(),from.getAddress(),from.getEmail(),
                from.getOrder().stream().map(orderUserDtoConverter::convert).collect(Collectors.toList())
                );
    }
}
