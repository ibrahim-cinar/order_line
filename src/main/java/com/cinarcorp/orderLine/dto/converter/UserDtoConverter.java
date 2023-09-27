package com.cinarcorp.orderLine.dto.converter;

import com.cinarcorp.orderLine.dto.UserDto;
import com.cinarcorp.orderLine.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {


    public UserDto convertToUserDto(User from){
        return  new UserDto(
                from.getFirstName(),
                from.getLastName(), from.getAddress(),
                from.getEmail()
                );
    }
}
