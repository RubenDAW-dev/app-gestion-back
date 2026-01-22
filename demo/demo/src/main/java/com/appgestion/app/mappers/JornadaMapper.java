package com.appgestion.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.appgestion.app.DTO.JornadaDTO;
import com.appgestion.app.model.JornadaEntity;

@Mapper(componentModel = "spring")
public interface JornadaMapper {
    @Mapping(target = "id_tarea", ignore = true)
    @Mapping(target = "id_trabajador", ignore = true)
    @Mapping(target = "id", ignore = true)
	JornadaEntity toEntity (JornadaDTO dto);
	
    @Mapping(target = "id_tarea", ignore = true)
    @Mapping(target = "id_trabajador", ignore = true)
	JornadaDTO toDto (JornadaEntity entity);
}
