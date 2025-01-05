package com.ecommerce.backend.services.concretes;

import com.ecommerce.backend.core.utils.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.dtos.requests.ProductRequestDTO;
import com.ecommerce.backend.dtos.responses.ProductResponseDTO;
import com.ecommerce.backend.entities.Product;
import com.ecommerce.backend.mappers.ProductMapper;
import com.ecommerce.backend.repositories.ProductRepository;
import com.ecommerce.backend.services.abstracts.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::responseFromProduct);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.responseFromProduct(product);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.productFromRequest(productRequestDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.responseFromProduct(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productMapper.updateProductFromRequest(productRequestDTO, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.responseFromProduct(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    @Override
    public ProductResponseDTO updateStock(Long id, Integer stock) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStock(stock);
        Product updatedProduct = productRepository.save(product);
        return productMapper.responseFromProduct(updatedProduct);
    }
}
