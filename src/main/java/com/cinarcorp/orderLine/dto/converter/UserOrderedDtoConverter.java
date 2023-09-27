package com.cinarcorp.orderLine.dto.converter;

import com.cinarcorp.orderLine.dto.UserOrderedDto;
import com.cinarcorp.orderLine.model.User;
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
