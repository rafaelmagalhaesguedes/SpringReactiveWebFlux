package com.web.flux.controllers;

import com.web.flux.services.CategoryService;
import com.web.flux.services.dto.CategoryResponseDTO;
import com.web.flux.controllers.dto.CategoryRequestDTO;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Criar uma nova categoria
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) {
        return categoryService.create(requestDTO);
    }

    // Obter todas as categorias com paginação
    @GetMapping
    public Flux<CategoryResponseDTO> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return categoryService.getByPage(page, size);
    }

    // Obter uma categoria pelo ID
    @GetMapping("/{id}")
    public Mono<CategoryResponseDTO> getCategoryById(@PathVariable UUID id) {
        return categoryService.getById(id);
    }

    // Atualizar uma categoria existente
    @PutMapping("/{id}")
    public Mono<CategoryResponseDTO> updateCategory(@PathVariable UUID id, @Valid @RequestBody CategoryRequestDTO requestDTO) {
        return categoryService.update(id, requestDTO);
    }

    // Deletar uma categoria
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCategory(@PathVariable UUID id) {
        return categoryService.delete(id);
    }
}
