package com.web.flux.services.impl;

import com.web.flux.services.ProductService;
import com.web.flux.entities.Product;
import com.web.flux.mapper.ProductMapper;
import com.web.flux.repositories.ProductRepository;
import com.web.flux.services.dto.ProductResponseDTO;
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

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Mono<ProductResponseDTO> createProduct(ProductRequestDTO requestDTO) {
        Product product = productMapper.toEntity(requestDTO);
        product.setId(UUID.randomUUID());
        product.setCreatedAt(LocalDateTime.now());

        return productRepository.save(product)
                .map(productMapper::toResponseDTO);
    }

    @Override
    public Flux<ProductResponseDTO> getProductsByPage(int page, int size) throws ProductNotFoundException {
        return productRepository.findAll()
                .skip((long) page * size)
                .take(size)
                .map(productMapper::toResponseDTO)
                .switchIfEmpty(Mono.error(ProductNotFoundException::new));
    }

    @Override
    public Mono<ProductResponseDTO> getProductById(UUID id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .map(productMapper::toResponseDTO)
                .switchIfEmpty(Mono.error(ProductNotFoundException::new));
    }

    @Override
    public Mono<ProductResponseDTO> updateProduct(UUID id, ProductRequestDTO requestDTO) throws ProductNotFoundException {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(ProductNotFoundException::new))
                .flatMap(existingProduct -> {
                    existingProduct.setName(requestDTO.getName());
                    existingProduct.setDescription(requestDTO.getDescription());
                    existingProduct.setSaleValue(requestDTO.getSaleValue());
                    existingProduct.setPurchaseValue(requestDTO.getPurchaseValue());
                    existingProduct.setQuantity(requestDTO.getQuantity());
                    return productRepository.save(existingProduct);
                })
                .doOnNext(product -> product.setUpdatedAt(LocalDateTime.now()))
                .map(productMapper::toResponseDTO);
    }

    @Override
    public Mono<Void> deleteProduct(UUID id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(ProductNotFoundException::new))
                .flatMap(product -> productRepository.deleteById(id));
    }
}
