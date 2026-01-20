package com.appgestion.app.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.appgestion.app.DTO.TrabajadorAllDTO;
import com.appgestion.app.DTO.TrabajadorCategoriaDTO;
import com.appgestion.app.DTO.TrabajadorDTO;
import com.appgestion.app.model.TrabajadorEntity;

@Mapper(componentModel = "spring")
public interface TrabajadorMapper {


    @Mapping(source = "categoria.id", target = "id_categoria")
    TrabajadorDTO toDto(TrabajadorEntity entity);

    @Mapping(source = "id_categoria", target = "categoria.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "jornada", ignore = true)
    TrabajadorEntity toEntity(TrabajadorDTO dto);

    @Mapping(source = "categoria.id", target = "id_categoria")
    TrabajadorAllDTO toAllDto(TrabajadorEntity entity);

    @Mapping(source = "id_categoria", target = "categoria.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "jornada", ignore = true)
    TrabajadorEntity toEntity(TrabajadorAllDTO dto);

    List<TrabajadorAllDTO> toAllDTOList(List<TrabajadorEntity> entities);


    @Mapping(source = "categoria", target = "id_categoria")
    TrabajadorCategoriaDTO toCategoriaDto(TrabajadorEntity entity);

    @Mapping(source = "id_categoria", target = "categoria")
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "jornada", ignore = true)
    @Mapping(target = "categoria.trabajadores", ignore = true)
    TrabajadorEntity toEntity(TrabajadorCategoriaDTO dto);

    List<TrabajadorCategoriaDTO> toCategoriaDTOList(List<TrabajadorEntity> entities);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "jornada", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    void updateEntityFromDTO(TrabajadorAllDTO dto, @MappingTarget TrabajadorEntity entity);

}