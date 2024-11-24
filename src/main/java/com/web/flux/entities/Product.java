package com.web.flux.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tb_products")
public class Product {
    @Id
    private UUID id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal saleValue;
    private BigDecimal purchaseValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}