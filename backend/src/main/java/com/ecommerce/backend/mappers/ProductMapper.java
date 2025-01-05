package com.ecommerce.backend.mappers;

import com.ecommerce.backend.dtos.requests.ProductRequestDTO;
import com.ecommerce.backend.dtos.responses.ProductResponseDTO;
import com.ecommerce.backend.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product productFromRequest(ProductRequestDTO request);

    ProductResponseDTO responseFromProduct(Product product);

    void updateProductFromRequest(ProductRequestDTO request, @MappingTarget Product product);
}
