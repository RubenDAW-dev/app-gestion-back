package com.appgestion.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.appgestion.app.DTO.TareaMaterialDTO;
import com.appgestion.app.model.TareaMaterialEntity;


@Mapper(componentModel = "spring")
public interface TareaMaterialMapper {
    @Mapping(target = "id", ignore=true)
    @Mapping(target = "id_tarea", ignore = true)
    @Mapping(target = "id_material", ignore = true)
    TareaMaterialEntity toEntity(TareaMaterialDTO dto);

    @Mapping(target = "id_tarea", source = "id_tarea.id")
    @Mapping(target = "id_material", source = "id_material.id")
    TareaMaterialDTO toDto(TareaMaterialEntity entidad);
}
