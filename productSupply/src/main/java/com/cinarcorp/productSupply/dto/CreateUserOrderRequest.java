package com.cinarcorp.productSupply.dto;

import com.cinarcorp.productSupply.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
