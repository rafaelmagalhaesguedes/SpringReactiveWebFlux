package com.web.flux.controllers;

import com.web.flux.services.ProductService;
import com.web.flux.services.dto.ProductResponseDTO;
import com.web.flux.controllers.dto.ProductRequestDTO;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Criar um novo produto
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        return productService.create(requestDTO);
    }

    // Obter todos os produtos com paginação
    @GetMapping
    public Flux<ProductResponseDTO> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getByPage(page, size);
    }

    // Obter um produto pelo ID
    @GetMapping("/{id}")
    public Mono<ProductResponseDTO> getProductById(@PathVariable UUID id) {
        return productService.getById(id);
    }

    // Atualizar um produto existente
    @PutMapping("/{id}")
    public Mono<ProductResponseDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody ProductRequestDTO requestDTO) {
        return productService.update(id, requestDTO);
    }

    // Deletar um produto
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable UUID id) {
        return productService.delete(id);
    }
}
