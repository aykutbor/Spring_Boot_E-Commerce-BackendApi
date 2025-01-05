package com.ecommerce.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "orders")
    private List<Product> products;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;


    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;


    public enum OrderStatus {
        PENDING,  // Sipariş bekliyor
        SHIPPED,  // Sipariş gönderildi
        DELIVERED, // Sipariş teslim edildi
        CANCELLED  // Sipariş iptal edildi
    }

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
