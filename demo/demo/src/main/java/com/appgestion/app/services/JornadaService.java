package com.appgestion.app.services;

import org.springframework.stereotype.Service;

import com.appgestion.app.DTO.JornadaDTO;
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

}
