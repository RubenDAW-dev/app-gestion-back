package com.appgestion.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.appgestion.app.DTO.JornadaDTO;
import com.appgestion.app.model.JornadaEntity;

@Mapper(componentModel = "spring")
public interface JornadaMapper {
	//@Mapping(source = "id_trabajador",target = "id_trabajador.id")
	//JornadaEntity toEntity (JornadaDTO dto);
	
	//JornadaDTO toDto (JornadaEntity entity);
}
