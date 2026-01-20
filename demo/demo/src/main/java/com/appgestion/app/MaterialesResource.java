package com.appgestion.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appgestion.app.DTO.MaterialDTO;
import com.appgestion.app.services.MaterialService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/material")
public class MaterialesResource {

	private final MaterialService materialservice;
	
	@PostMapping("/add")
	public ResponseEntity<MaterialDTO> addMaterial(@RequestBody MaterialDTO materialdto){
		MaterialDTO saved = materialservice.add(materialdto);
	    return new ResponseEntity<>(saved, HttpStatus.CREATED);

	}
}
