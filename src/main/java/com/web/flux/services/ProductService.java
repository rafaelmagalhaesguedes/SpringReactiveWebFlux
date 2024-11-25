package com.web.flux.services;

import com.web.flux.services.dto.ProductResponseDTO;
import com.web.flux.services.exceptions.ProductNotFoundException;
import com.web.flux.controllers.dto.ProductRequestDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductService {
    Mono<ProductResponseDTO> create(ProductRequestDTO requestDTO);
    Flux<ProductResponseDTO> getByPage(int page, int size);
    Mono<ProductResponseDTO> getById(UUID id);
    Mono<ProductResponseDTO> update(UUID id, ProductRequestDTO requestDTO);
    Mono<Void> delete(UUID id);
}
