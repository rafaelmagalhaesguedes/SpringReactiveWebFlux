package com.web.flux.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Converte DTO para Entidade
    public <D, T> T toEntity(D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    // Converte Entidade para DTO
    public <T, D> D toDTO(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}