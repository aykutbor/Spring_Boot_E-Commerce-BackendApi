package com.ecommerce.backend.services.concretes;

import com.ecommerce.backend.dtos.requests.OrderItemRequestDTO;
import com.ecommerce.backend.dtos.responses.OrderItemResponseDTO;
import com.ecommerce.backend.entities.OrderItem;
import com.ecommerce.backend.mappers.OrderItemMapper;
import com.ecommerce.backend.repositories.OrderItemRepository;
import com.ecommerce.backend.services.abstracts.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    @Override
    public OrderItemResponseDTO createOrderItem(OrderItemRequestDTO request) {
        OrderItem orderItem = orderItemMapper.orderItemFromRequest(request);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        return orderItemMapper.responseFromOrderItem(savedOrderItem);
    }

    @Override
    public OrderItemResponseDTO getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        return orderItemMapper.responseFromOrderItem(orderItem);
    }

    @Override
    public List<OrderItemResponseDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();

        return orderItems.stream()
                .map(orderItemMapper::responseFromOrderItem)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemResponseDTO updateOrderItem(Long id, OrderItemRequestDTO request) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        orderItem = orderItemMapper.orderItemFromRequest(request);
        orderItem.setId(id); // ID'yi koruyarak güncelleme yapıyoruz

        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);

        return orderItemMapper.responseFromOrderItem(updatedOrderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        orderItemRepository.delete(orderItem);
    }
}
