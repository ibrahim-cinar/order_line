package com.cinarcorp.orderLine.repository;

import com.cinarcorp.orderLine.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    Order getOrderById(String id);

    @Query("SELECT o FROM Order o WHERE o.totalPaid > :totalPaid")
    List<Order> getOrderBiggerThanTotalPaid(@Param("totalPaid") int totalPaid);

}
