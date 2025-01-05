package com.ecommerce.backend.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private Long userId;
    private List<Long> productId; // Kullanıcıdan gelen ürün id'leri
    private Double totalPrice;
    private Long addressId;
}
