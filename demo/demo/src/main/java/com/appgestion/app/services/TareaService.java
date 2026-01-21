package com.appgestion.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.appgestion.app.DTO.TareaAllDTO;
import com.appgestion.app.DTO.TareaDTO;
import com.appgestion.app.DTO.TareaTablaDTO;
import com.appgestion.app.mappers.TareaMapper;
import com.appgestion.app.model.CategoriaEntity;
import com.appgestion.app.model.ProyectoEntity;
import com.appgestion.app.model.TareaEntity;
import com.appgestion.app.repo.ProyectoRepo;
import com.appgestion.app.repo.TareaRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TareaService {
	
	private final TareaRepo tarearepo;
	private TareaMapper tareamapper;
	private ProyectoRepo proyectorepo;

	public TareaDTO addTarea(TareaDTO tareadto) {
		TareaEntity tarea = tareamapper.toEntity(tareadto);
		
	    ProyectoEntity proyecto = proyectorepo.findById(tareadto.getId_proyecto()).orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
	    tarea.setId_proyecto(proyecto);
	    
	    if (tareadto.getTarea_padre() != null) {
	        tarearepo.findById(tareadto.getTarea_padre()).ifPresent(tarea::setTareaPadre);
	    }
	    tarearepo.save(tarea);
	    return tareadto;
	}

	public Page<TareaTablaDTO> findAllTareas(Pageable pageable) {
		return tarearepo.findAll(pageable).map(tareamapper::toTablaDto);
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
}
