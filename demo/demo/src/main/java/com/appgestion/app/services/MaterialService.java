package com.appgestion.app.services;

import org.springframework.stereotype.Service;

import com.appgestion.app.DTO.MaterialDTO;
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

}
