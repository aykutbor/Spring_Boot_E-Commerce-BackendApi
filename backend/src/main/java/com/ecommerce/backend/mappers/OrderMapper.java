package com.ecommerce.backend.mappers;

import com.ecommerce.backend.dtos.requests.OrderRequestDTO;
import com.ecommerce.backend.dtos.requests.OrderStatusRequestDTO;
import com.ecommerce.backend.dtos.responses.OrderResponseDTO;
import com.ecommerce.backend.entities.Order;
import com.ecommerce.backend.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "address.id", source = "addressId")
    @Mapping(target = "products", source = "productId")  // 'productId' listesini 'products' listesine bağlayacağız
    Order orderFromRequest(OrderRequestDTO request);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "addressId", source = "address.id")
    @Mapping(target = "products", source = "products")  // 'products' listesini 'productId' listesine bağlayacağız
    OrderResponseDTO responseFromOrder(Order order);

    @Mapping(source = "status", target = "orderStatus")
    Order toEntity(OrderStatusRequestDTO orderStatusRequestDTO);

    @Mapping(source = "orderStatus", target = "status")
    OrderResponseDTO toResponseDTO(Order order);

    // Helper method to map product IDs to Product entities
    @Mapping(target = "id", source = "productId")
    Product mapProduct(Long productId);

    // Helper method to map product IDs list to Products list
    default List<Product> mapProductIdsToProducts(List<Long> productIds) {
        return productIds.stream()
                .map(this::mapProduct) // mapProduct metodunu kullanarak ID'leri Product'a dönüştür
                .collect(Collectors.toList());
    }

}
