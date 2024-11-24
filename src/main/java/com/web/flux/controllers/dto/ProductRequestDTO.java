package com.web.flux.controllers.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "The product name cannot be blank")
    private String name;

    @NotBlank(message = "The product description cannot be blank")
    private String description;

    @NotNull(message = "The product quantity cannot be null")
    @Min(value = 0, message = "The product quantity must be greater than or equal to 0")
    private Integer quantity;

    @NotNull(message = "The sale value price cannot be null")
    @Min(value = 0, message = "The sale value price must be greater than or equal to 0")
    private BigDecimal saleValue;

    @NotNull(message = "The purchase value price cannot be null")
    @Min(value = 0, message = "The purchase value price must be greater than or equal to 0")
    private BigDecimal purchaseValue;
}