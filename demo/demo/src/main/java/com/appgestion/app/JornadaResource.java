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

import com.appgestion.app.DTO.JornadaAllDTO;
import com.appgestion.app.DTO.JornadaDTO;
import com.appgestion.app.DTO.JornadaFiltro;
import com.appgestion.app.DTO.JornadaNombresDTO;
import com.appgestion.app.DTO.ProyectoAllDTO;
import com.appgestion.app.services.JornadaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/jornada")
public class JornadaResource {

	private final JornadaService jornadaservice;

	@PostMapping("/add")
	public ResponseEntity<JornadaDTO> addJornada(@RequestBody JornadaDTO jornadadto) {
		jornadaservice.addJornada(jornadadto);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@GetMapping("/all")
	public ResponseEntity<Page<JornadaNombresDTO>> getAllJornadas(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<JornadaNombresDTO> jornadas = jornadaservice.findAllJornadas(pageable);
		return new ResponseEntity<>(jornadas, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<Page<JornadaNombresDTO>> searchJornada(@ModelAttribute JornadaFiltro jornadafiltro,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<JornadaNombresDTO> result = jornadaservice.search(jornadafiltro, pageable);
		return ResponseEntity.ok(result);
	}

	@PutMapping("/update")
	public ResponseEntity<JornadaAllDTO> updateProyecto(@RequestBody JornadaAllDTO jornadadto) {
		jornadaservice.updateJornada(jornadadto);
		return new ResponseEntity<>(jornadadto, HttpStatus.CREATED);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteJornada(@PathVariable Long id) {
		jornadaservice.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/count/no_validadas")
	public ResponseEntity<Long> count_no_validadas() {
		Long count = jornadaservice.count_no_validadas();
		return new ResponseEntity<>(count, HttpStatus.OK);

	}

	@GetMapping("/all/no_validadas")
	public ResponseEntity<Page<JornadaNombresDTO>> getNoValidadas(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<JornadaNombresDTO> lista = jornadaservice.getNoValidadas(pageable);
		return new ResponseEntity<>(lista, HttpStatus.OK);

	}

}
