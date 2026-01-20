package com.appgestion.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.appgestion.app.DTO.MaterialAllDTO;
import com.appgestion.app.DTO.MaterialDTO;
import com.appgestion.app.model.MaterialEntity;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

	MaterialDTO toDTO (MaterialEntity entity);
	
    @Mapping(target = "tareas",ignore = true)
    @Mapping(target = "id",ignore = true)
	MaterialEntity toEntity (MaterialDTO dto);
    
    MaterialAllDTO toAllDto (MaterialEntity entity);
}
