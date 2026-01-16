package com.appgestion.app;

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

import com.appgestion.app.DTO.UsuarioDTO;
import com.appgestion.app.services.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioResource {
	private final UsuarioService usuarioservice;
	
	@PostMapping("/add")
	public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody UsuarioDTO usuariodto) {
		System.out.println(usuariodto);
		usuarioservice.addUsuario(usuariodto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@GetMapping("/all")
	public ResponseEntity<Page<UsuarioDTO>> getAllUsuarios(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<UsuarioDTO> result = usuarioservice.findAllUsuarios(pageable);
		return ResponseEntity.ok(result);
	}
	@PutMapping("/update")
	public ResponseEntity<UsuarioDTO> updateUsuario(@RequestBody UsuarioDTO usuariodto){
		usuarioservice.updateUsuario(usuariodto);
		return new ResponseEntity<>(usuariodto,HttpStatus.CREATED);	
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable ("id") Long id){
		usuarioservice.deleteUsuarioById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/search")
		public ResponseEntity<Page<UsuarioDTO>> searchUsuarios(@RequestParam String texto, @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
			Pageable pageable = PageRequest.of(page, size);
			Page<UsuarioDTO> result = usuarioservice.searchByNombre(texto,pageable);
			return ResponseEntity.ok(result);
	}
	
}
