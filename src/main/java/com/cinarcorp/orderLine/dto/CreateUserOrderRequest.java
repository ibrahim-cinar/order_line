package com.cinarcorp.orderLine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component

public class CreateUserOrderRequest {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private OrderUserDto order ;
}
