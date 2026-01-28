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

import com.appgestion.app.DTO.ProyectoAllDTO;
import com.appgestion.app.DTO.ProyectoDTO;
import com.appgestion.app.DTO.ProyectoFiltro;
import com.appgestion.app.DTO.ProyectoNombresDTO;
import com.appgestion.app.DTO.ProyectoTareasDTO;
import com.appgestion.app.services.ProyectoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/proyecto")
public class ProyectoResource {

	private final ProyectoService proyectoservice;

	@PostMapping("/add")
	public ResponseEntity<ProyectoDTO> addProyecto(@RequestBody ProyectoDTO proyectodto) {
		proyectoservice.addProyecto(proyectodto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<Page<ProyectoAllDTO>> getAllProyectos(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<ProyectoAllDTO> result = proyectoservice.findAllProyectos(pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/search")
	public ResponseEntity<Page<ProyectoAllDTO>> searchProyectos(@ModelAttribute ProyectoFiltro trabajadorfiltro,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<ProyectoAllDTO> result = proyectoservice.search(trabajadorfiltro,pageable);
		return ResponseEntity.ok(result);
	}
	@PutMapping("/update")
	public ResponseEntity<ProyectoAllDTO> updateProyecto(@RequestBody ProyectoAllDTO proyectodto){
		proyectoservice.updateProyecto(proyectodto);
		return new ResponseEntity<>(proyectodto, HttpStatus.CREATED);

	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProyecto (@PathVariable Long id){
		proyectoservice.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<ProyectoTareasDTO> getProyectoById (@PathVariable Long id){
		ProyectoTareasDTO proyecto = proyectoservice.findProyectoById(id);
		return new ResponseEntity<>(proyecto, HttpStatus.OK);
		}
	@GetMapping ("/count")
	public ResponseEntity<Long> getCountProyectos(){
		Long cant = proyectoservice.countProyectos();
		return new ResponseEntity<>(cant, HttpStatus.OK);
	}
	
	@GetMapping("/all/nombres")
	public ResponseEntity<List<ProyectoNombresDTO>> getNombresProyecto(){
		List<ProyectoNombresDTO> lista = proyectoservice.findNombresProyecto();
		return ResponseEntity.ok(lista);
	}
}
