package com.ecommerce.backend.mappers;

import com.ecommerce.backend.dtos.requests.OrderItemRequestDTO;
import com.ecommerce.backend.dtos.responses.OrderItemResponseDTO;
import com.ecommerce.backend.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {


    @Mapping(target = "order.id", source = "orderId")
    @Mapping(target = "product.id", source = "productId")
    OrderItem orderItemFromRequest(OrderItemRequestDTO request);

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "product.id")
    OrderItemResponseDTO responseFromOrderItem(OrderItem orderItem);
}
