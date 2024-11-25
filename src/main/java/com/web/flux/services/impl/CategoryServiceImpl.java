package com.web.flux.services.impl;

import com.web.flux.controllers.dto.CategoryRequestDTO;
import com.web.flux.entities.Category;
import com.web.flux.enums.CategoryType;
import com.web.flux.mapper.GenericMapper;
import com.web.flux.repositories.CategoryRepository;
import com.web.flux.services.CategoryService;
import com.web.flux.services.dto.CategoryResponseDTO;
import com.web.flux.services.exceptions.CategoryNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final GenericMapper mapper;

    @Override
    public Mono<CategoryResponseDTO> create(CategoryRequestDTO requestDTO) {
        return Mono.just(requestDTO)
                .map(dto -> {
                    Category category = mapper.toEntity(dto, Category.class);
                    category.setId(UUID.randomUUID());
                    category.setCreatedAt(LocalDateTime.now());
                    category.setType(CategoryType.valueOf(requestDTO.getType().toUpperCase()));
                    return category;
                })
                .onErrorMap(IllegalArgumentException.class, e -> new CategoryNotFoundException())
                .flatMap(repository::save)
                .map(savedCategory -> mapper.toDTO(savedCategory, CategoryResponseDTO.class));
    }

    @Override
    public Flux<CategoryResponseDTO> getByPage(int page, int size) {
        return repository.findAll()
                .skip((long) page * size)
                .take(size)
                .collectList()
                .flatMapMany(categories -> {
                    if (categories.isEmpty()) {
                        return Flux.error(new CategoryNotFoundException());
                    } else {
                        return Flux.fromIterable(categories)
                                .map(category -> mapper.toDTO(category, CategoryResponseDTO.class));
                    }
                });
    }

    @Override
    public Mono<CategoryResponseDTO> getById(UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(CategoryNotFoundException::new))
                .map(category -> mapper.toDTO(category, CategoryResponseDTO.class));
    }

    @Override
    public Mono<CategoryResponseDTO> update(UUID id, CategoryRequestDTO requestDTO) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(CategoryNotFoundException::new))
                .flatMap(existingCategory -> {
                    existingCategory.setType(CategoryType.valueOf(requestDTO.getType().toUpperCase()));
                    existingCategory.setDescription(requestDTO.getDescription());
                    return repository.save(existingCategory);
                })
                .map(category -> mapper.toDTO(category, CategoryResponseDTO.class));
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(CategoryNotFoundException::new))
                .flatMap(product -> repository.deleteById(id));
    }
}