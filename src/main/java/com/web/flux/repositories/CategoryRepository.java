package com.web.flux.repositories;

import com.web.flux.entities.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, UUID> {
}