package com.appgestion.app.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.appgestion.app.DTO.ProyectoDTOTarea;
import com.appgestion.app.DTO.TareaAddDTO;
import com.appgestion.app.DTO.TareaAllDTO;
import com.appgestion.app.DTO.TareaDTO;
import com.appgestion.app.DTO.TareaDTOTarea;
import com.appgestion.app.DTO.TareaLazyDTO;
import com.appgestion.app.DTO.TareaTablaDTO;
import com.appgestion.app.model.ProyectoEntity;
import com.appgestion.app.model.TareaEntity;

@Mapper(componentModel = "spring")
public interface TareaMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "id_proyecto", ignore = true)
	@Mapping(target = "tareaPadre", ignore = true)
	@Mapping(target = "subtareas", ignore = true)
	@Mapping(target = "materiales", ignore = true)
	@Mapping(target = "jornada", ignore = true)
	TareaEntity toEntity(TareaDTO dto);

	@Mapping(source = "id_proyecto", target = "id_proyecto")
	@Mapping(source = "tareaPadre", target = "tarea_padre")
	@Mapping(target = "subtareas_ids", expression = "java(getSubtareasIds(entity))")
	TareaDTO toDto(TareaEntity entity);
	
    default ProyectoDTOTarea mapProyecto(ProyectoEntity entity) {
        if (entity == null) return null;
        return new ProyectoDTOTarea(entity.getId(), entity.getNombre());
    }

    default TareaDTOTarea mapTareaPadre(TareaEntity entity) {
        if (entity == null) return null;
        return new TareaDTOTarea(entity.getId(), entity.getNombre());
    }

	default List<Long> getSubtareasIds(TareaEntity entity) {
		if (entity.getSubtareas() == null) {
			return List.of();
		}
		return entity.getSubtareas().stream().map(TareaEntity::getId).toList();
	}

	TareaTablaDTO toTablaDto(TareaEntity entity);

	@Mapping(target = "id_proyecto", ignore = true)
	@Mapping(target = "tareaPadre", ignore = true)
	@Mapping(target = "subtareas", ignore = true)
	@Mapping(target = "materiales", ignore = true)
	@Mapping(target = "jornada", ignore = true)
	void updateEntityFromDTO(TareaAllDTO tareadto, @MappingTarget TareaEntity tarea);
	
	@Mapping(source = "id_proyecto.id", target = "id_proyecto") 
	@Mapping(source = "tareaPadre.id", target = "tarea_padre") 
	@Mapping(target = "subtareas", ignore = true)
	TareaLazyDTO toLazyDto(TareaEntity entity);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "id_proyecto", ignore = true)
	@Mapping(target = "tareaPadre", ignore = true)
	@Mapping(target = "subtareas", ignore = true)
	@Mapping(target = "materiales", ignore = true)
	@Mapping(target = "jornada", ignore = true)
	TareaEntity AddtoEntity(TareaAddDTO dto);


}
