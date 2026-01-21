package com.appgestion.app;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appgestion.app.DTO.CategoriaAllDTO;
import com.appgestion.app.DTO.CategoriaDTO;
import com.appgestion.app.DTO.CategoriaNombresDTO;
import com.appgestion.app.services.CategoriaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/categoria")
public class CategoriaResource {
	private final CategoriaService categoriaservice;

	@PostMapping("/add")
	public ResponseEntity<CategoriaDTO> addCategoria(@RequestBody CategoriaDTO categoriadto){
	    CategoriaDTO saved = categoriaservice.addCategoria(categoriadto);
	    return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<CategoriaAllDTO>> getAllCategorias (@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<CategoriaAllDTO> result = categoriaservice.findAllCategorias(pageable);
		return ResponseEntity.ok(result);
	}
	@GetMapping("/search")
	public ResponseEntity<Page<CategoriaAllDTO>> searchCategorias (@RequestParam String texto,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<CategoriaAllDTO> result = categoriaservice.searchByNombre(texto, pageable);
		return ResponseEntity.ok(result);
	
	}
	@PutMapping("/update")
	public ResponseEntity<CategoriaAllDTO> updateCategoria(@RequestBody CategoriaAllDTO categoriadto){
		categoriaservice.updateCategoria(categoriadto);
		return new ResponseEntity<>(categoriadto, HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id){
		categoriaservice.deleteCategoriaById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/all/nombres")
	public ResponseEntity<List<CategoriaNombresDTO>> getNombresCategoria(){
		List<CategoriaNombresDTO> lista = categoriaservice.findNombresCategoria();
		return ResponseEntity.ok(lista);
	}
	
}