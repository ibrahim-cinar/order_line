package com.cinarcorp.orderLine.repository;

import com.cinarcorp.orderLine.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    Product getProductById(String id);
    @Query("SELECT o FROM Product o WHERE o.price > :price")
    List<Product> getProductsBiggerThanPrice(@Param("price") int price);

    List<Product> findProductsByProductName(String productName);
}
