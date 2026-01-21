package com.appgestion.app;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appgestion.app.DTO.TareaAllDTO;
import com.appgestion.app.DTO.TareaDTO;
import com.appgestion.app.DTO.TareaTablaDTO;
import com.appgestion.app.services.TareaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/tarea")
public class TareaResource {
	private final TareaService tareaservice;
	
	@PostMapping("/add")
	public ResponseEntity<TareaDTO> addTarea(@RequestBody TareaDTO tareadto){
		TareaDTO saved = tareaservice.addTarea(tareadto);
	    return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<TareaTablaDTO>> getAllTareas(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<TareaTablaDTO> tareas = tareaservice.findAllTareas(pageable);
		return new ResponseEntity<>(tareas, HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<TareaAllDTO> updateTarea(@RequestBody TareaAllDTO tareadto){
		tareaservice.updateTarea(tareadto);
		return new ResponseEntity<>(tareadto, HttpStatus.CREATED);

	}
}
