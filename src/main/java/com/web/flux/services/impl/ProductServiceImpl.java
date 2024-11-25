package com.web.flux.services.impl;

import com.web.flux.mapper.GenericMapper;
import com.web.flux.services.ProductService;
import com.web.flux.entities.Product;
import com.web.flux.repositories.ProductRepository;
import com.web.flux.services.dto.ProductResponseDTO;
import com.web.flux.services.exceptions.CategoryNotFoundException;
import com.web.flux.services.exceptions.ProductNotFoundException;
import com.web.flux.controllers.dto.ProductRequestDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final GenericMapper mapper;

    @Override public Mono<ProductResponseDTO> create(ProductRequestDTO requestDTO) {
        return Mono.just(requestDTO)
                .map(dto -> {
                    Product product = mapper.toEntity(dto, Product.class);
                    product.setId(UUID.randomUUID());
                    product.setCreatedAt(LocalDateTime.now());
                    product.setCategoryId(requestDTO.getCategoryId());
                    return product;
                })
                .flatMap(repository::save)
                .map(savedProduct -> mapper.toDTO(savedProduct, ProductResponseDTO.class));
    }

    @Override
    public Flux<ProductResponseDTO> getByPage(int page, int size) {
        return repository.findAll()
                .skip((long) page * size)
                .take(size)
                .map(product -> mapper.toDTO(product, ProductResponseDTO.class))
                .switchIfEmpty(Mono.error(ProductNotFoundException::new));
    }

    @Override
    public Mono<ProductResponseDTO> getById(UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(ProductNotFoundException::new))
                .map(product -> mapper.toDTO(product, ProductResponseDTO.class));
    }

    @Override
    public Mono<ProductResponseDTO> update(UUID id, ProductRequestDTO requestDTO) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(ProductNotFoundException::new))
                .flatMap(existingProduct -> {
                    existingProduct.setName(requestDTO.getName());
                    existingProduct.setDescription(requestDTO.getDescription());
                    existingProduct.setSaleValue(requestDTO.getSaleValue());
                    existingProduct.setPurchaseValue(requestDTO.getPurchaseValue());
                    existingProduct.setQuantity(requestDTO.getQuantity());
                    return repository.save(existingProduct);
                })
                .doOnNext(product -> product.setUpdatedAt(LocalDateTime.now()))
                .map(product -> mapper.toDTO(product, ProductResponseDTO.class));
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(ProductNotFoundException::new))
                .flatMap(product -> repository.deleteById(id));
    }
}
