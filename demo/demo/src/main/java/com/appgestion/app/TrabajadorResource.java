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

import com.appgestion.app.DTO.TareaDTO;
import com.appgestion.app.DTO.TareaJornadaDTO;
import com.appgestion.app.DTO.TrabajadorAllDTO;
import com.appgestion.app.DTO.TrabajadorCategoriaDTO;
import com.appgestion.app.DTO.TrabajadorDTO;
import com.appgestion.app.DTO.TrabajadorFiltro;
import com.appgestion.app.DTO.TrabajadorUsuarioDTO;
import com.appgestion.app.services.TrabajadorService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/trabajador")
public class TrabajadorResource {
	private final TrabajadorService trabajadorservice;

	@PostMapping("/add")
	public ResponseEntity<TrabajadorAllDTO> addTrabajador(@RequestBody TrabajadorDTO trabajadordto){
		TrabajadorAllDTO saved = trabajadorservice.addTrabajador(trabajadordto);
	    return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	@GetMapping("/all")
	public ResponseEntity<Page<TrabajadorCategoriaDTO>> getAllTrabajadores (@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<TrabajadorCategoriaDTO> trabajadores = trabajadorservice.findAllTrabajadores(pageable);
		return new ResponseEntity<>(trabajadores, HttpStatus.OK);
	}
	@GetMapping("/search")
	public ResponseEntity<Page<TrabajadorCategoriaDTO>> getTrabajadorFiltro (@ModelAttribute TrabajadorFiltro trabajadorfiltro,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<TrabajadorCategoriaDTO> trabajadores = trabajadorservice.search(trabajadorfiltro,pageable);
		return new ResponseEntity<>(trabajadores, HttpStatus.OK);
	
	}
	@PutMapping("/update")
	public ResponseEntity<TrabajadorAllDTO> updateTrabajador(@RequestBody TrabajadorAllDTO trabajadordto){
		trabajadorservice.updateTrabajador(trabajadordto);
		return new ResponseEntity<>(trabajadordto, HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTrabajador(@PathVariable Long id){
		trabajadorservice.deleteTrabajadorById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<TrabajadorCategoriaDTO> getTrabajador (@PathVariable Long id){
		TrabajadorCategoriaDTO trabajador = trabajadorservice.findTrabajadorById(id);
		return new ResponseEntity<>(trabajador, HttpStatus.OK);
		}
	@GetMapping ("/count")
	public ResponseEntity<Long> getCountProyectos(){
		Long cant = trabajadorservice.countTrabajador();
		return new ResponseEntity<>(cant, HttpStatus.OK);
	}
	@GetMapping("/{id}/tareas")
	public ResponseEntity<Page<TareaJornadaDTO>> getTareasTrabajador(@PathVariable Long id,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<TareaJornadaDTO> lista = trabajadorservice.getTareasDeTrabajador(id,pageable);
		return ResponseEntity.ok(lista);
	}
}
