package com.web.flux.services;

import com.web.flux.controllers.dto.CategoryRequestDTO;
import com.web.flux.services.dto.CategoryResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CategoryService {
    Mono<CategoryResponseDTO> create(CategoryRequestDTO requestDTO);
    Flux<CategoryResponseDTO> getByPage(int page, int size);
    Mono<CategoryResponseDTO> getById(UUID id);
    Mono<CategoryResponseDTO> update(UUID id, CategoryRequestDTO requestDTO);
    Mono<Void> delete(UUID id);
}