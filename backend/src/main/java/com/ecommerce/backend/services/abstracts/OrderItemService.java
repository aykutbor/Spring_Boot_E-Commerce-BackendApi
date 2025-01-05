package com.ecommerce.backend.services.abstracts;

import com.ecommerce.backend.dtos.requests.OrderItemRequestDTO;
import com.ecommerce.backend.dtos.responses.OrderItemResponseDTO;

import java.util.List;

public interface OrderItemService {
    OrderItemResponseDTO createOrderItem(OrderItemRequestDTO request);

    OrderItemResponseDTO getOrderItemById(Long id);

    List<OrderItemResponseDTO> getAllOrderItems();

    OrderItemResponseDTO updateOrderItem(Long id, OrderItemRequestDTO request);

    void deleteOrderItem(Long id);
}
