package com.cinarcorp.productSupply.controller;

import com.cinarcorp.productSupply.dto.CreateOrderRequest;
import com.cinarcorp.productSupply.dto.CreateUserOrderRequest;
import com.cinarcorp.productSupply.dto.OrderDto;
import com.cinarcorp.productSupply.dto.OrderUserDto;
import com.cinarcorp.productSupply.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/create")
    public ResponseEntity<OrderUserDto> createNewOrder(@RequestBody CreateUserOrderRequest createUserOrderRequest) {
        OrderUserDto orderUserDto = orderService.createNewOrder(createUserOrderRequest);
        return ResponseEntity.ok(orderUserDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
