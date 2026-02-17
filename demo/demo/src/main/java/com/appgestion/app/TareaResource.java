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
import com.appgestion.app.DTO.TareaAddDTO;
import com.appgestion.app.DTO.TareaAllDTO;
import com.appgestion.app.DTO.TareaDTO;
import com.appgestion.app.DTO.TareaFiltro;
import com.appgestion.app.DTO.TareaLazyDTO;
import com.appgestion.app.DTO.TareaNombresDTO;
import com.appgestion.app.services.TareaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/tarea")
public class TareaResource {
	private final TareaService tareaservice;
	
	@PostMapping("/add")
	public ResponseEntity<TareaAddDTO> addTarea(@RequestBody TareaAddDTO tareadto){
		TareaAddDTO saved = tareaservice.addTarea(tareadto);
	    return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<TareaDTO>> getAllTareas(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<TareaDTO> tareas = tareaservice.findAllTareas(pageable);
		return new ResponseEntity<>(tareas, HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<TareaAllDTO> updateTarea(@RequestBody TareaAllDTO tareadto){
		tareaservice.updateTarea(tareadto);
		return new ResponseEntity<>(tareadto, HttpStatus.CREATED);

	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTarea (@PathVariable Long id){
		tareaservice.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<TareaLazyDTO> getTareaById(@PathVariable Long id) {
	    TareaLazyDTO dto = tareaservice.findTareaById(id);
	    return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	@GetMapping("/search")
	public ResponseEntity<Page<TareaDTO>> searchTareas(@ModelAttribute TareaFiltro tareafiltro,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<TareaDTO> result = tareaservice.search(tareafiltro,pageable);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/all/nombres")
	public ResponseEntity<List<TareaNombresDTO>> getNombresTarea(@RequestParam int id){
		List<TareaNombresDTO> lista = tareaservice.findNombresTarea(id);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/nombre/{id}")
	public ResponseEntity<String> getNombreTareaById(@PathVariable Long id){
		String tarea = tareaservice.getNombreTareaById(id);
		return ResponseEntity.ok(tarea);
	}

}
