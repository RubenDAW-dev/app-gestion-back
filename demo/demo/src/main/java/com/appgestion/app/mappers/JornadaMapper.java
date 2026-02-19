package com.appgestion.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.appgestion.app.DTO.JornadaAllDTO;
import com.appgestion.app.DTO.JornadaDTO;
import com.appgestion.app.DTO.JornadaNombresDTO;
import com.appgestion.app.DTO.JornadaValidarDTO;
import com.appgestion.app.DTO.TareaDTOTarea;
import com.appgestion.app.DTO.TareaValidarDTO;
import com.appgestion.app.DTO.TrabajadorNombreDTO;
import com.appgestion.app.model.JornadaEntity;
import com.appgestion.app.model.TareaEntity;
import com.appgestion.app.model.TrabajadorEntity;

@Mapper(componentModel = "spring")
public interface JornadaMapper {
    @Mapping(target = "id_tarea", ignore = true)
    @Mapping(target = "id_trabajador", ignore = true)
    @Mapping(target = "id", ignore = true)
	JornadaEntity toEntity (JornadaDTO dto);
	
    @Mapping(source = "id_tarea.id",target = "id_tarea")
    @Mapping(source = "id_trabajador.id",target = "id_trabajador")
	JornadaDTO toDto (JornadaEntity entity);
    
    @Mapping(source = "id_tarea", target = "id_tarea", qualifiedByName = "mapTarea")
    @Mapping(source = "id_trabajador", target = "id_trabajador", qualifiedByName = "mapTrabajador")
    JornadaNombresDTO toNombresDto(JornadaEntity entity);
    
    @Mapping(source = "id_tarea", target = "id_tarea", qualifiedByName = "mapTareaValidar")
    @Mapping(source = "id_trabajador", target = "id_trabajador", qualifiedByName = "mapTrabajador")
    JornadaValidarDTO toValidarDto(JornadaEntity entity);
    
    @Named("mapTarea")
    default TareaDTOTarea mapTarea(TareaEntity tarea) {
        if (tarea == null) return null;
        return new TareaDTOTarea(tarea.getId(), tarea.getNombre());
    }
    
    @Named("mapTareaValidar")
    default TareaValidarDTO mapTareaValidar(TareaEntity tarea) {
        if (tarea == null) return null;
        return new TareaValidarDTO(tarea.getId(), tarea.getNombre(), tarea.getId_proyecto().getId());
    }
    
    @Named("mapTrabajador")
    default TrabajadorNombreDTO mapTrabajador(TrabajadorEntity trabajador) {
        if (trabajador == null) return null;
        return new TrabajadorNombreDTO(trabajador.getId(), trabajador.getDNI());
    }
    
    @Mapping(target = "id_tarea", ignore = true)
    @Mapping(target = "id_trabajador", ignore = true)
	void updateEntityfromDTO(JornadaAllDTO jornadadto,@MappingTarget JornadaEntity jornada);
}
