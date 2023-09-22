package com.cinarcorp.productSupply.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderRepositoryTest {
    @Autowired
   private  OrderRepository orderRepository;

    @Test
    public void getOrderById(){
        var order = orderRepository.getOrderById("c740b4a9-9c0a-401a-997a-ac232157240d");
        System.out.println(order.getUser().getId());
    }
}