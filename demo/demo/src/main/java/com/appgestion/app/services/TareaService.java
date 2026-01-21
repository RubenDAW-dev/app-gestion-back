package com.appgestion.app.services;

import org.springframework.stereotype.Service;
import com.appgestion.app.DTO.TareaDTO;
import com.appgestion.app.mappers.TareaMapper;
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

}
