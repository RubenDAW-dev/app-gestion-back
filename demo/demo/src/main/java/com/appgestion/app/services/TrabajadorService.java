package com.appgestion.app.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.appgestion.app.DTO.TrabajadorAllDTO;
import com.appgestion.app.DTO.TrabajadorCategoriaDTO;
import com.appgestion.app.DTO.TrabajadorDTO;
import com.appgestion.app.DTO.TrabajadorFiltro;
import com.appgestion.app.exception.UserNotFoundException;
import com.appgestion.app.mappers.TrabajadorMapper;
import com.appgestion.app.model.CategoriaEntity;
import com.appgestion.app.model.TrabajadorEntity;
import com.appgestion.app.repo.CategoriaRepo;
import com.appgestion.app.repo.TrabajadorRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TrabajadorService {

	private final TrabajadorRepo trabajadorrepo;
	private final TrabajadorMapper trabajadormapper;
    private final CategoriaRepo categoriarepo;


	
	public TrabajadorDTO addTrabajador(TrabajadorDTO trabajadordto) {
        TrabajadorEntity trabajador = trabajadormapper.toEntity(trabajadordto);
        CategoriaEntity categoria = categoriarepo.findById(trabajadordto.getId_categoria()).orElseThrow(() ->new RuntimeException("Categoria no encontrada"));
        trabajador.setCategoria(categoria);
        trabajadorrepo.save(trabajador);
		return trabajadordto;
	}


	public Page<TrabajadorCategoriaDTO> findAllTrabajadores(Pageable pageable) {
		return trabajadorrepo.findAll(pageable).map(trabajadormapper::toCategoriaDto);
	}


	//public Page<TrabajadorAllDTO> searchByNombre(String texto, Pageable pageable) {
	//	return trabajadorrepo.findByNombreContainingIgnoreCase(texto, pageable).map(trabajadormapper::toAllDto);
	//}


	public void deleteTrabajadorById(Long id) {
		Optional<TrabajadorEntity>trabajador = trabajadorrepo.findById(id);
		if (trabajador.isEmpty()) {
			new UserNotFoundException("Trabajador no encontrado");
		}
		trabajadorrepo.deleteById(id);
	}


    public void updateTrabajador(TrabajadorAllDTO dto) {
        TrabajadorEntity trabajador = trabajadorrepo.findById(dto.getId()).orElseThrow(() ->new RuntimeException("Trabajador no encontrado"));
        trabajadormapper.updateEntityFromDTO(dto, trabajador);

        if (dto.getId_categoria() != null) {
            CategoriaEntity categoria = categoriarepo.findById(dto.getId_categoria()).orElseThrow(() ->new RuntimeException("Categoria no encontrada"));
            trabajador.setCategoria(categoria);
        }

        trabajadorrepo.save(trabajador);
    }


	public TrabajadorAllDTO findTrabajadorById(Long id) {
		TrabajadorEntity trabajador = trabajadorrepo.findById(id).orElseThrow(() -> new UserNotFoundException("Trabajador con id "+ id +" no se ha encontrado"));
		TrabajadorAllDTO trabajadordto = trabajadormapper.toAllDto(trabajador);
		return trabajadordto;

	}
	public Page<TrabajadorCategoriaDTO> search(TrabajadorFiltro filtro, Pageable pageable) {
	    return trabajadorrepo.findByFiltros(
	            filtro.getTexto(),
	            filtro.getEstado(),
	            filtro.getId_categoria(),
	            pageable
	    ).map(trabajadormapper::toCategoriaDto);
	}
}
