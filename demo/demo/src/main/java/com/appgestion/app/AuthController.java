package com.appgestion.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appgestion.app.DTO.LoginRequestDTO;
import com.appgestion.app.DTO.LoginResponseDTO;
import com.appgestion.app.DTO.UpdateConstrasena;
import com.appgestion.app.services.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
	
	private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = usuarioService.login(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/update-password")
    public ResponseEntity<LoginResponseDTO> updatePassword(@RequestBody UpdateConstrasena request) {
    	LoginResponseDTO response = usuarioService.updatePassword(request);
        return ResponseEntity.ok(response);
    }
}
