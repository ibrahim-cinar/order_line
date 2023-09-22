package com.cinarcorp.productSupply.dto.converter;

import com.cinarcorp.productSupply.dto.UserDto;
import com.cinarcorp.productSupply.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
