package com.cinarcorp.orderLine.controller;

import com.cinarcorp.orderLine.dto.CreateUserOrderRequest;
import com.cinarcorp.orderLine.dto.OrderDto;
import com.cinarcorp.orderLine.dto.OrderUserDto;
import com.cinarcorp.orderLine.service.OrderService;
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
    @GetMapping("/complete/{isComplete}")
    public ResponseEntity<List<OrderDto>> getAllOrderAndIsComplete(@PathVariable boolean isComplete) {
        return ResponseEntity.ok(orderService.getAllOrderAndIsComplete(isComplete));
    }
    @GetMapping("/complete/{orderId}/{isComplete}")
    public ResponseEntity<OrderDto> getOrderByIdAndIsComplete(@PathVariable String orderId,@PathVariable boolean isComplete) {
        return ResponseEntity.ok(orderService.getOrderByIdAndIsComplete(orderId,isComplete));
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
