package com.web.flux.services;

import com.web.flux.services.dto.ProductResponseDTO;
import com.web.flux.services.exceptions.ProductNotFoundException;
import com.web.flux.controllers.dto.ProductRequestDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductService {
    Mono<ProductResponseDTO> createProduct(ProductRequestDTO requestDTO);
    Flux<ProductResponseDTO> getProductsByPage(int page, int size);
    Mono<ProductResponseDTO> getProductById(UUID id) throws ProductNotFoundException;
    Mono<ProductResponseDTO> updateProduct(UUID id, ProductRequestDTO requestDTO) throws ProductNotFoundException;
    Mono<Void> deleteProduct(UUID id) throws ProductNotFoundException;
}
