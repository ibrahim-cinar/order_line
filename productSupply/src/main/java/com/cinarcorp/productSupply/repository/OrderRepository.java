package com.cinarcorp.productSupply.repository;

import com.cinarcorp.productSupply.model.Order;
import com.cinarcorp.productSupply.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,String> {
    Order getOrderById(String id);

}
