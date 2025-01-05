package com.ecommerce.backend.repositories;

import com.ecommerce.backend.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // Sipariş ID'sine göre OrderItem'ları bulma
    List<OrderItem> findByOrderId(Long orderId);

    // Ürün ID'sine göre OrderItem'ları bulma
    List<OrderItem> findByProductId(Long productId);
}
