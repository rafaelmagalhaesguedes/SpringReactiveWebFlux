package com.web.flux.mapper;

import com.web.flux.services.dto.ProductResponseDTO;
import com.web.flux.entities.Product;
import com.web.flux.controllers.dto.ProductRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Converte ProductRequestDTO para Product
    public Product toEntity(ProductRequestDTO dto) {
        return modelMapper.map(dto, Product.class);
    }

    // Converte Product para ProductResponseDTO
    public ProductResponseDTO toResponseDTO(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }
}
