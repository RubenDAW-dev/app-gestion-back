package com.appgestion.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appgestion.app.DTO.JornadaDTO;
import com.appgestion.app.services.JornadaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/jornada")
public class JornadaResource {

	private final JornadaService jornadaservice;
	
	@PostMapping("/add")
	public ResponseEntity<JornadaDTO> addJornada(@RequestBody JornadaDTO jornadadto){
		jornadaservice.addJornada(jornadadto);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
}
