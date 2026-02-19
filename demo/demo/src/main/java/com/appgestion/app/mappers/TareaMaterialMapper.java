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

    default TareaMaterialDTO toDto(TareaMaterialEntity entidad) {
        if (entidad == null) return null;
                
        TareaMaterialDTO dto = new TareaMaterialDTO();
        dto.setId_tarea(entidad.getId().getId_tarea());
        dto.setId_material(entidad.getId().getId_material());
        dto.setNombre_material(entidad.getId_material() != null ? entidad.getId_material().getNombre() : null);
        dto.setCantidad(entidad.getCantidad());
        return dto;
    }
}
