package com.appgestion.app.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.appgestion.app.DTO.ProyectoAllDTO;
import com.appgestion.app.DTO.ProyectoDTO;
import com.appgestion.app.model.ProyectoEntity;

@Mapper(componentModel = "spring")
public interface ProyectoMapper {

	ProyectoDTO toDto(ProyectoEntity entity);
	
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "tareas",ignore = true)
	ProyectoEntity toEntity(ProyectoDTO dto);
    
    ProyectoAllDTO EntitytoAll (ProyectoEntity alldto);
    
    @Mapping(target = "tareas",ignore = true)
    ProyectoEntity AlltoEntity (ProyectoAllDTO alldto);
    
    List<ProyectoAllDTO> toAllDTOList (List<ProyectoEntity> entities);

    @Mapping(target = "tareas",ignore = true)
	void updateEntityFromDTO(ProyectoAllDTO proyectodto, @MappingTarget ProyectoEntity proyecto);
}
