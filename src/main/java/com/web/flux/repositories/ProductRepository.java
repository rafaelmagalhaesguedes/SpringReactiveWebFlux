package com.web.flux.repositories;

import com.web.flux.entities.Product;
import com.web.flux.services.dto.ProductResponseDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, UUID> {

    Mono<Product> findByName(String name);

    Flux<ProductResponseDTO> findAllBy(PageRequest pageRequest);
}