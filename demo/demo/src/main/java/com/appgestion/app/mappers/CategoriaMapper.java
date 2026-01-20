package com.appgestion.app.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.appgestion.app.DTO.CategoriaAllDTO;
import com.appgestion.app.DTO.CategoriaDTO;
import com.appgestion.app.DTO.CategoriaNombresDTO;
import com.appgestion.app.model.CategoriaEntity;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

	CategoriaDTO toDto(CategoriaEntity entity);

    CategoriaAllDTO toAllDto(CategoriaEntity entity);

    List<CategoriaAllDTO> toAllDTOList(List<CategoriaEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trabajadores", ignore = true)
    CategoriaEntity toEntity(CategoriaDTO dto);

    @Mapping(target = "trabajadores", ignore = true)
    CategoriaEntity toEntity(CategoriaAllDTO dto);
    
    List<CategoriaNombresDTO> toNombresDTOList(List<CategoriaEntity> entities); 

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trabajadores", ignore = true)
    void updateEntityFromDTO(CategoriaAllDTO dto, @MappingTarget CategoriaEntity entity);
}
