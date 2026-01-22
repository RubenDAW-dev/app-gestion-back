package com.appgestion.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.appgestion.app.DTO.LoginRequestDTO;
import com.appgestion.app.DTO.LoginResponseDTO;
import com.appgestion.app.DTO.UpdateConstrasena;
import com.appgestion.app.DTO.UsuarioDTO;
import com.appgestion.app.mappers.UsuarioMapper;
import com.appgestion.app.model.TrabajadorEntity;
import com.appgestion.app.model.UsuarioEntity;
import com.appgestion.app.repo.TrabajadorRepo;
import com.appgestion.app.repo.UsuarioRepo;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class UsuarioService {
	
	private final UsuarioRepo usuariorepo;
	private final TrabajadorRepo trabajadorrepo;
	private final UsuarioMapper usuariomapper;
	
	public void addUsuario(UsuarioDTO usuariodto) {
		TrabajadorEntity trabajador = trabajadorrepo.findById(usuariodto.getId_trabajador()).orElseThrow(() -> new RuntimeException("Trabajador no existe"));
		UsuarioEntity usuario = usuariomapper.toEntity(usuariodto);
		usuario.setTrabajador(trabajador);
		usuariorepo.save(usuario);
	}

	public Page<UsuarioDTO> findAllUsuarios(Pageable pageable) {
		return usuariorepo.findAll(pageable).map(usuariomapper::toDTO);
	}

	public void updateUsuario(UsuarioDTO usuariodto) {
		UsuarioEntity usuario = usuariorepo.findById(usuariodto.getId_trabajador()).orElseThrow(() ->new RuntimeException("Usuario no encontrado"));
		usuariomapper.updateEntityFromDTO(usuariodto, usuario);
		
		if(usuariodto.getId_trabajador() != null) {
			TrabajadorEntity trabajador = trabajadorrepo.findById(usuariodto.getId_trabajador()).orElseThrow(() -> new RuntimeException("Trabajador no existe"));
			usuario.setTrabajador(trabajador);
		}
		usuariorepo.save(usuario);
	}

	public void deleteUsuarioById(Long id) {
		UsuarioEntity usuario = usuariorepo.findById(id).orElseThrow(() ->new RuntimeException("Usuario no encontrado"));
		usuariorepo.delete(usuario);
	}

	public Page<UsuarioDTO> searchByNombre(String texto, Pageable pageable) {
		return usuariorepo.findByUsuario(texto,pageable).map(usuariomapper::toDTO);
	}

	public LoginResponseDTO login(LoginRequestDTO request) {

        UsuarioEntity usuario = usuariorepo.findByUsuario(request.getUsuario()).orElseThrow(() -> new RuntimeException("Usuario no existe"));
        
        if (!usuario.getContrasena().equals(request.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        TrabajadorEntity trabajador = usuario.getTrabajador();

        return new LoginResponseDTO(trabajador.getId(),usuario.isRol(),trabajador.getNombre(),usuario.isCambio_contrasena());
	}

	public LoginResponseDTO updatePassword(UpdateConstrasena request) {
	    UsuarioEntity usuario = usuariorepo.findById(request.getId_usuario()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

	    TrabajadorEntity trabajador = usuario.getTrabajador();
	    
	    usuario.setContrasena(request.getNueva_contrasena());
	    usuario.setCambio_contrasena(true);
	    usuariorepo.save(usuario);

        return new LoginResponseDTO(trabajador.getId(),usuario.isRol(),trabajador.getNombre(),usuario.isCambio_contrasena());

	}

}
