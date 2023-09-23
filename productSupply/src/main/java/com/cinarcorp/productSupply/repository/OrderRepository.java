package com.cinarcorp.productSupply.repository;

import com.cinarcorp.productSupply.model.Order;
import com.cinarcorp.productSupply.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    Order getOrderById(String id);

    @Query("SELECT o FROM Order o WHERE o.totalPaid > :totalPaid")
    List<Order> getOrderBiggerThanTotalPaid(@Param("totalPaid") int totalPaid);

}
