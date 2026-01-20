
package com.appgestion.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.appgestion.app.DTO.CategoriaAllDTO;
import com.appgestion.app.DTO.CategoriaDTO;
import com.appgestion.app.DTO.CategoriaNombresDTO;
import com.appgestion.app.exception.UserNotFoundException;
import com.appgestion.app.mappers.CategoriaMapper;
import com.appgestion.app.model.CategoriaEntity;
import com.appgestion.app.repo.CategoriaRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoriaService {

	private final CategoriaRepo categoriarepo;
	private final CategoriaMapper categoriaMapper;

	public CategoriaDTO addCategoria(CategoriaDTO categoriadto) {
		CategoriaEntity categoria = categoriaMapper.toEntity(categoriadto);
		categoriarepo.save(categoria);
		return categoriadto;
	}

	public Page<CategoriaAllDTO> findAllCategorias(Pageable pageable) {
		return categoriarepo.findAll(pageable).map(categoriaMapper::toAllDto);
	}

	// public CategoriaAllDTO findCategoriaById(Long id) {
	// CategoriaEntity categoria = categoriarepo.findById(id).orElseThrow(() -> new
	// UserNotFoundException("Categoria con id "+ id +" no se ha encontrado"));
	// CategoriaAllDTO categoriadto = categoriaMapper.EntitytoAll(categoria);
	// return categoriadto;
	// }

	public void updateCategoria(CategoriaAllDTO dto) {

        CategoriaEntity categoria = categoriarepo.findById(dto.getId()).orElseThrow(() ->new RuntimeException("Categoria no encontrada"));
        categoriaMapper.updateEntityFromDTO(dto, categoria);
        categoriarepo.save(categoria);
    }

	public void deleteCategoriaById(Long id) {
		Optional<CategoriaEntity> categoriaEntity = categoriarepo.findById(id);
		if (categoriaEntity.isEmpty()) {
			new UserNotFoundException("Categoria no encontrado");
		}
		categoriarepo.deleteById(id);

	}

	public Page<CategoriaAllDTO> searchByNombre(String texto, Pageable pageable) {
		return categoriarepo.findByNombreContainingIgnoreCase(texto, pageable).map(categoriaMapper::toAllDto);
	}

	public List<CategoriaNombresDTO> findNombresCategoria() {
		List<CategoriaEntity> entities = categoriarepo.findAll();
		List<CategoriaNombresDTO> dto = categoriaMapper.toNombresDTOList(entities);
		return dto;
	}
}
