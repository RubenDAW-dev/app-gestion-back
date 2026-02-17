package com.appgestion.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.appgestion.app.DTO.UsuarioDTO;
import com.appgestion.app.model.UsuarioEntity;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	@Mapping(source = "trabajador.id", target = "id_trabajador")
	UsuarioDTO toDTO (UsuarioEntity entity);
	
    @Mapping(target = "trabajador",ignore = true)
    @Mapping(target = "id",ignore = true)
	UsuarioEntity toEntity(UsuarioDTO dto);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trabajador", ignore = true)
    void updateEntityFromDTO(UsuarioDTO dto, @MappingTarget UsuarioEntity entity);
}
 