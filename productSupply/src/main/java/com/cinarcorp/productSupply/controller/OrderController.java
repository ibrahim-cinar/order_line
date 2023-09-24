package com.cinarcorp.productSupply.controller;

import com.cinarcorp.productSupply.dto.CreateOrderRequest;
import com.cinarcorp.productSupply.dto.CreateUserOrderRequest;
import com.cinarcorp.productSupply.dto.OrderDto;
import com.cinarcorp.productSupply.dto.OrderUserDto;
import com.cinarcorp.productSupply.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getAllOrder(){
        return ResponseEntity.ok(orderService.getAllOrder());
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
    @GetMapping("/totalPaid/{totalPaid}")
    public ResponseEntity<List<OrderDto>> getOrderBiggerThanTotalPaid(@PathVariable int totalPaid){
        return ResponseEntity.ok(orderService.getOrderBiggerThanTotalPaid(totalPaid));
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
