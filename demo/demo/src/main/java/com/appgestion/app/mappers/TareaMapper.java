package com.appgestion.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.appgestion.app.DTO.TareaDTO;
import com.appgestion.app.model.TareaEntity;

@Mapper(componentModel = "spring")
public interface TareaMapper {

	@Mapping(target = "id_proyecto", ignore = true)
	@Mapping(target = "jornada", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "tareaPadre", ignore = true)
	@Mapping(target = "subtareas", ignore = true)
	@Mapping(target = "materiales", ignore = true)
	TareaEntity toEntity (TareaDTO tareadto);
	
	@Mapping(target = "id_proyecto", ignore = true)
	@Mapping(target = "tarea_padre", ignore = true)
	TareaDTO toDto (TareaEntity entity);
}
