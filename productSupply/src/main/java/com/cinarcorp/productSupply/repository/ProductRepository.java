package com.cinarcorp.productSupply.repository;

import com.cinarcorp.productSupply.model.Order;
import com.cinarcorp.productSupply.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
    Product getProductById(String id);

}
