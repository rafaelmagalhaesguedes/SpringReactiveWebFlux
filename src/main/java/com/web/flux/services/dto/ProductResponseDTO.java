package com.web.flux.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal saleValue;
    private BigDecimal purchaseValue;
    private LocalDateTime createdAt;
    private CategoryResponseDTO category;
}