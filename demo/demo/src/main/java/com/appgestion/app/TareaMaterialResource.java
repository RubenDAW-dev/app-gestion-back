package com.appgestion.app;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appgestion.app.DTO.TareaMaterialDTO;
import com.appgestion.app.services.TareaMaterialService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/tareamaterial")
public class TareaMaterialResource {
	private final TareaMaterialService tareamaterialservice;

	@PostMapping("add")
	public ResponseEntity<TareaMaterialDTO> addTareaMaterial(@RequestBody TareaMaterialDTO tareamaterialdto) {
		TareaMaterialDTO saved = tareamaterialservice.addRegistro(tareamaterialdto);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	@PutMapping ("update")
	public ResponseEntity<TareaMaterialDTO> updateRegistro(@RequestBody TareaMaterialDTO tareamaterialdto){
		TareaMaterialDTO result = tareamaterialservice.updateRegistro(tareamaterialdto);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	@DeleteMapping ("delete/")
	public ResponseEntity<?>deleteRegistro (@RequestParam Long id_tarea,@RequestParam Long id_material){
		tareamaterialservice.deleteRegistro(id_tarea,id_material);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping ("/search")
	public ResponseEntity<Page<TareaMaterialDTO>> getRegistrosFiltro (@ModelAttribute TareaMaterialDTO filtro,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<TareaMaterialDTO> registros = tareamaterialservice.search(filtro,pageable);
		return new ResponseEntity<>(registros, HttpStatus.OK);
	
	}
	@GetMapping("/tarea/{id}")
	public List<TareaMaterialDTO> getByTarea(@PathVariable Long id) {
	    return tareamaterialservice.findByTarea(id);
	}
}
