package com.appgestion.app.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import com.appgestion.app.DTO.MaterialAllDTO;
import com.appgestion.app.DTO.MaterialDTO;
import com.appgestion.app.DTO.MaterialFiltro;
import com.appgestion.app.mappers.MaterialMapper;
import com.appgestion.app.model.MaterialEntity;
import com.appgestion.app.repo.MaterialRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MaterialService {
	private final MaterialRepo materialrepo;
	private final MaterialMapper materialmapper;

	public MaterialDTO add(MaterialDTO materialdto) {
		MaterialEntity material = materialmapper.toEntity(materialdto);
		materialrepo.save(material);
		return materialdto;
	}

	public Page<MaterialAllDTO> findAllMateriales(Pageable pageable) {
		return materialrepo.findAll(pageable).map(materialmapper::toAllDto);
	}

	public Page<MaterialAllDTO> search(MaterialFiltro filtro, Pageable pageable) {
		return materialrepo.findByFiltros(
				filtro.getTexto(),
				filtro.getStock_maximo(),
				filtro.getPrecio_minimo(),
				filtro.getPrecio_maximo(),
				pageable
				).map(materialmapper::toAllDto);
	}

	public void updateMaterial(MaterialAllDTO materialdto) {
	    MaterialEntity material = materialrepo.findById(materialdto.getId()).orElseThrow(() -> new RuntimeException("Material no encontrado"));
	    materialmapper.updateEntityFromDTO(materialdto, material);

	    materialrepo.save(material);
	}

	public void deleteById(Long id) {
	    MaterialEntity material = materialrepo.findById(id).orElseThrow(() -> new RuntimeException("Material no encontrado"));
	    materialrepo.delete(material);
	}

}
