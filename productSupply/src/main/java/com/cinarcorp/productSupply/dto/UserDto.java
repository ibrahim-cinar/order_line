package com.cinarcorp.productSupply.dto;

import com.cinarcorp.productSupply.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    //private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
}
