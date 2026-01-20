package com.appgestion.app;

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

import com.appgestion.app.DTO.TrabajadorAllDTO;
import com.appgestion.app.DTO.TrabajadorCategoriaDTO;
import com.appgestion.app.DTO.TrabajadorDTO;
import com.appgestion.app.DTO.TrabajadorFiltro;
import com.appgestion.app.services.TrabajadorService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/trabajador")
public class TrabajadorResource {
	private final TrabajadorService trabajadorservice;

	@PostMapping("/add")
	public ResponseEntity<TrabajadorDTO> addTrabajador(@RequestBody TrabajadorDTO trabajadordto){
	    TrabajadorDTO saved = trabajadorservice.addTrabajador(trabajadordto);
	    return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	@GetMapping("/all")
	public ResponseEntity<Page<TrabajadorCategoriaDTO>> getAllTrabajadores (@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<TrabajadorCategoriaDTO> trabajadores = trabajadorservice.findAllTrabajadores(pageable);
		return new ResponseEntity<>(trabajadores, HttpStatus.OK);
	}
	@GetMapping("/search")
	public ResponseEntity<Page<TrabajadorAllDTO>> getTrabajadorFiltro (@ModelAttribute TrabajadorFiltro trabajadorfiltro,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<TrabajadorAllDTO> trabajadores = trabajadorservice.search(trabajadorfiltro,pageable);
		return new ResponseEntity<>(trabajadores, HttpStatus.OK);
	
	}
	@PutMapping("/update")
	public ResponseEntity<TrabajadorAllDTO> updateCategoria(@RequestBody TrabajadorAllDTO trabajadordto){
		trabajadorservice.updateTrabajador(trabajadordto);
		return new ResponseEntity<>(trabajadordto, HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable("id") Long id){
		trabajadorservice.deleteTrabajadorById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("find/{id}")
	public ResponseEntity<TrabajadorAllDTO> getCategoriaById (@PathVariable("id") Long id){
		TrabajadorAllDTO trabajador = trabajadorservice.findTrabajadorById(id);
		return new ResponseEntity<>(trabajador, HttpStatus.OK);
		}
}
