package com.appgestion.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appgestion.app.DTO.TareaDTO;
import com.appgestion.app.DTO.TrabajadorAllDTO;
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
	
}
