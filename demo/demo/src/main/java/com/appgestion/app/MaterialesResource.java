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

import com.appgestion.app.DTO.MaterialAllDTO;
import com.appgestion.app.DTO.MaterialDTO;
import com.appgestion.app.DTO.MaterialFiltro;
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
	@GetMapping("/all")
	public ResponseEntity<Page<MaterialAllDTO>>getAllMateriales(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<MaterialAllDTO> result = materialservice.findAllMateriales(pageable);
		return ResponseEntity.ok(result);
	}
	@GetMapping("/search")
	public ResponseEntity<Page<MaterialAllDTO>> searchMaterial (@ModelAttribute MaterialFiltro materialfiltro,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<MaterialAllDTO> result = materialservice.search(materialfiltro,pageable);
		return ResponseEntity.ok(result);	
	}
	@PutMapping("/update")
	public ResponseEntity<MaterialAllDTO> updateMaterial(@RequestBody MaterialAllDTO materialdto){
		materialservice.updateMaterial(materialdto);
		return new ResponseEntity<>(materialdto,HttpStatus.CREATED);
	}
	@DeleteMapping ("/delete/{id}")
	public ResponseEntity<?> deleteMaterial (@PathVariable ("id") Long id){
		materialservice.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
