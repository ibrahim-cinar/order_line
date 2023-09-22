package com.cinarcorp.productSupply.dto;

import com.cinarcorp.productSupply.model.Order;
import com.cinarcorp.productSupply.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateOrderRequest {

    private String userId;
    private Order order;
    private List<Product> product;
}
