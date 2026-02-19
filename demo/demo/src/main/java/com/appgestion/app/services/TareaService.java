package com.appgestion.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.appgestion.app.DTO.TareaAddDTO;
import com.appgestion.app.DTO.TareaAllDTO;
import com.appgestion.app.DTO.TareaDTO;
import com.appgestion.app.DTO.TareaFiltro;
import com.appgestion.app.DTO.TareaLazyDTO;
import com.appgestion.app.DTO.TareaNombresDTO;
import com.appgestion.app.DTO.TareaValidarDTO;
import com.appgestion.app.mappers.TareaMapper;
import com.appgestion.app.model.ProyectoEntity;
import com.appgestion.app.model.TareaEntity;
import com.appgestion.app.repo.JornadaRepo;
import com.appgestion.app.repo.ProyectoRepo;
import com.appgestion.app.repo.TareaRepo;
import com.appgestion.app.repo.TrabajadorRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TareaService {
	
	private TareaRepo tarearepo;
	private TareaMapper tareamapper;
	private ProyectoRepo proyectorepo;
	private JornadaRepo jornadarepo;
	private TrabajadorRepo trabajadorrepo;

	public TareaAddDTO addTarea(TareaAddDTO tareadto) {
		TareaEntity tarea = tareamapper.AddtoEntity(tareadto);
		
	    ProyectoEntity proyecto = proyectorepo.findById(tareadto.getId_proyecto()).orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
	    tarea.setId_proyecto(proyecto);
	    
	    if (tareadto.getTarea_padre() != null) {
	        tarearepo.findById(tareadto.getTarea_padre()).ifPresent(tarea::setTareaPadre);
	    }
	    tarearepo.save(tarea);
	    return tareadto;
	}

	public Page<TareaDTO> findAllTareas(Pageable pageable) {
		return tarearepo.findAll(pageable).map(tareamapper::toDto);
	}

	public void updateTarea(TareaAllDTO tareadto) {
		TareaEntity tarea = tarearepo.findById(tareadto.getId()).orElseThrow(() ->new RuntimeException("Tarea no encontrado"));
		tareamapper.updateEntityFromDTO(tareadto,tarea);
		
        if (tareadto.getId_proyecto() != null) {
            ProyectoEntity proyecto = proyectorepo.findById(tareadto.getId_proyecto()).orElseThrow(() ->new RuntimeException("Proyecto no encontrada"));
            tarea.setId_proyecto(proyecto);
        }
        if (tareadto.getTarea_padre() != null) {
            TareaEntity tarea_padre = tarearepo.findById(tareadto.getTarea_padre()).orElseThrow(() ->new RuntimeException("Tarea no encontrada"));
            tarea.setTareaPadre(tarea_padre);
        }else {
        	tarea.setTareaPadre(null);
        }
        tarearepo.save(tarea);
        
	}

	public void deleteById(Long id) {
		TareaEntity tarea = tarearepo.findById(id).orElseThrow(() ->new RuntimeException("Tarea no encontrado"));
		tarearepo.delete(tarea);
	}

	public TareaLazyDTO findTareaById(Long id) {
		TareaEntity tarea =  tarearepo.findById(id).orElseThrow(() ->new RuntimeException("Tarea no encontrado"));
		TareaLazyDTO dto = tareamapper.toLazyDto(tarea);
		dto.setSubtareas( tarea.getSubtareas().stream() .map(tareamapper::toLazyDto) .toList() );
		dto.setNum_subtareas(tarea.getSubtareas().size());
		return dto;
	}

	public Page<TareaDTO> search(TareaFiltro filtro, Pageable pageable) {
	    return tarearepo.findByFiltros(
	            filtro.getTerm(),
	            filtro.getTipo(),
	            filtro.getEstado(),
	            filtro.getFecha_inicio_desde(),
	            filtro.getFecha_inicio_hasta(),
	            filtro.getFecha_fin_desde(),
	            filtro.getFecha_fin_hasta(),
	            filtro.getPadre(),
	            filtro.getProyecto(),
	            pageable
	    ).map(tareamapper::toDto);
	}

	public List<TareaNombresDTO> findNombresTarea(int id) {
		List<TareaEntity> entities = tarearepo.findById_proyecto(id);
		List<TareaNombresDTO> dto = tareamapper.toNombresDTOList(entities);
		return dto;
	}
	
	public List<TareaValidarDTO> findNombresTareaValidar(int id) {
		List<TareaEntity> entities = tarearepo.findById_proyecto(id);
		List<TareaValidarDTO> dto = new ArrayList<TareaValidarDTO>();
		for (TareaEntity entity : entities) {
			TareaValidarDTO tareaValidarDTO = new TareaValidarDTO(entity.getId(), entity.getNombre(), entity.getId_proyecto().getId());
			dto.add(tareaValidarDTO);
		}
		
		return dto;
	}

	public String getNombreTareaById(Long id) {
		TareaEntity tarea = tarearepo.findById(id).orElseThrow(() ->new RuntimeException("Tarea no encontrado"));
		return tarea.getNombre();
	}

}
