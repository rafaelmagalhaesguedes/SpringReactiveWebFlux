package com.web.flux.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {

    @NotBlank(message = "The category type cannot be blank")
    private String type;

    @NotBlank(message = "The category description cannot be blank")
    private String description;
}