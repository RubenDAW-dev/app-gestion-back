package com.appgestion.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.appgestion.app.DTO.ProyectoAllDTO;
import com.appgestion.app.DTO.ProyectoDTO;
import com.appgestion.app.mappers.ProyectoMapper;
import com.appgestion.app.model.ProyectoEntity;
import com.appgestion.app.repo.ProyectoRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProyectoService {
	private final ProyectoRepo proyectorepo;
	private final ProyectoMapper proyectomapper;
	
	
	public void addProyecto(ProyectoDTO proyectodto) {
		ProyectoEntity proyecto = proyectomapper.toEntity(proyectodto);
		proyectorepo.save(proyecto);
	}


	public Page<ProyectoAllDTO> findAllProyectos(Pageable pageable) {
		return proyectorepo.findAll(pageable).map(proyectomapper::EntitytoAll);
	}


	public Page<ProyectoAllDTO> searchByNombre(String texto, Pageable pageable) {
		return proyectorepo.findByNombreContainingIgnoreCase(texto,pageable).map(proyectomapper::EntitytoAll);
	}

}
