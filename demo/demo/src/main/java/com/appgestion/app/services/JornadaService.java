package com.appgestion.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.appgestion.app.DTO.JornadaAllDTO;
import com.appgestion.app.DTO.JornadaDTO;
import com.appgestion.app.DTO.JornadaFiltro;
import com.appgestion.app.DTO.JornadaNombresDTO;
import com.appgestion.app.mappers.JornadaMapper;
import com.appgestion.app.model.JornadaEntity;
import com.appgestion.app.model.TareaEntity;
import com.appgestion.app.model.TrabajadorEntity;
import com.appgestion.app.repo.JornadaRepo;
import com.appgestion.app.repo.TareaRepo;
import com.appgestion.app.repo.TrabajadorRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class JornadaService {
	private JornadaRepo jornadarepo;
	private JornadaMapper jornadamapper;
	private TareaRepo tarearepo;
	private TrabajadorRepo trabajorrepo;

	public void addJornada(JornadaDTO jornadadto) {
			JornadaEntity jornada = jornadamapper.toEntity(jornadadto);
			TareaEntity tarea = tarearepo.findById(jornadadto.getId_tarea()).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
			TrabajadorEntity trabajador = trabajorrepo.findById(jornadadto.getId_trabajador()).orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

			jornada.setId_tarea(tarea);
			jornada.setId_trabajador(trabajador);

	}

	public Page<JornadaNombresDTO> findAllJornadas(Pageable pageable) {
		return jornadarepo.findAll(pageable).map(jornadamapper::toNombresDto);
	}

	public Page<JornadaNombresDTO> search(JornadaFiltro filtro, Pageable pageable) {
		// TODO Auto-generated method stub
		return jornadarepo.findByFiltros(
				filtro.getFecha_desde(),
				filtro.getFecha_hasta(),
				filtro.isValidado(),
				pageable
				).map(jornadamapper::toNombresDto);
	}

	public void updateJornada(JornadaAllDTO jornadadto) {
		JornadaEntity jornada = jornadarepo.findById(jornadadto.getId()).orElseThrow(() -> new RuntimeException("Jornada no encontrada"));
		jornadamapper.updateEntityfromDTO(jornadadto,jornada);
		
		TrabajadorEntity trabajador = trabajorrepo.findById(jornadadto.getId_trabajador()).orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));
		TareaEntity tarea = tarearepo.findById(jornadadto.getId_tarea()).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

		jornada.setId_tarea(tarea);
		jornada.setId_trabajador(trabajador);
		
		jornadarepo.save(jornada);
	}

	public void deleteById(Long id) {
		JornadaEntity jornada = jornadarepo.findById(id).orElseThrow(() -> new RuntimeException("Jornada no encontrada"));
		jornadarepo.delete(jornada);
	}

}
