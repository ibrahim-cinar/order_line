package com.cinarcorp.orderLine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_products")
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID")
    private String id;

    private String productName;
    private String description;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "order_id",nullable = false)
    @JsonIgnore
    private Order order;


    public Product(String productName, String description, int price) {
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
