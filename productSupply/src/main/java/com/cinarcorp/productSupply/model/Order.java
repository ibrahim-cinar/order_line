package com.cinarcorp.productSupply.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_orders")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private List<Product> product;

    private int totalPaid;

    private int piece;
    private LocalDateTime orderDate;
    private boolean isComplete;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return totalPaid == order.totalPaid && piece == order.piece && isComplete == order.isComplete && Objects.equals(id, order.id) && Objects.equals(user, order.user) && Objects.equals(product, order.product) && Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, product, totalPaid, piece, orderDate, isComplete);
    }

    public Order(User user,int totalPaid, int piece, LocalDateTime orderDate,boolean isComplete ) {
        this.user=user;
        this.totalPaid = totalPaid;
        this.piece = piece;
        this.orderDate = orderDate;
        this.isComplete=isComplete;
    }

    public Order(List<Product> product, int totalPaid, int piece, LocalDateTime orderDate, boolean isComplete) {
        this.product = product;
        this.totalPaid = totalPaid;
        this.piece = piece;
        this.orderDate = orderDate;
        this.isComplete = isComplete;
    }

    public Order(User user, List<Product> product, int totalPaid, int piece, LocalDateTime orderDate, boolean isComplete) {
        this.user = user;
        this.product = product;
        this.totalPaid = totalPaid;
        this.piece = piece;
        this.orderDate = orderDate;
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", product=" + product +
                ", totalPaid=" + totalPaid +
                ", piece=" + piece +
                ", orderDate=" + orderDate +
                ", isComplete=" + isComplete +
                '}';
    }
}
