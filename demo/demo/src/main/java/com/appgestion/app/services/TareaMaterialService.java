package com.appgestion.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appgestion.app.DTO.TareaMaterialDTO;
import com.appgestion.app.mappers.TareaMaterialMapper;
import com.appgestion.app.model.MaterialEntity;
import com.appgestion.app.model.TareaEntity;
import com.appgestion.app.model.TareaMaterialEntity;
import com.appgestion.app.model.TareasMaterialId;
import com.appgestion.app.repo.MaterialRepo;
import com.appgestion.app.repo.TareaMaterialRepo;
import com.appgestion.app.repo.TareaRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TareaMaterialService {
	private final TareaMaterialRepo tareamaterialrepo;
	private final TareaRepo tarearepo;
	private final MaterialRepo materialrepo;
	private final TareaMaterialMapper tareamaterialmapper;

	public TareaMaterialDTO addRegistro(TareaMaterialDTO dto) {

		TareaEntity tarea = tarearepo.findById(dto.getId_tarea())
				.orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
		MaterialEntity material = materialrepo.findById(dto.getId_material())
				.orElseThrow(() -> new RuntimeException("Material no encontrado"));

		TareasMaterialId pk = new TareasMaterialId(tarea.getId(), material.getId());

		TareaMaterialEntity entidad = tareamaterialrepo.findById(pk).orElse(null);

		int incremento = dto.getCantidad();
		if (material.getStock() - incremento < 0) {
			throw new RuntimeException("El stock no puede ser negativo");
		}

		if (entidad == null) {

			entidad = new TareaMaterialEntity();
			entidad.setId(pk);
			entidad.setId_tarea(tarea);
			;
			entidad.setId_material(material);
			entidad.setCantidad(incremento);
		} else {

			entidad.setCantidad(entidad.getCantidad() + incremento);
		}
		material.setStock(material.getStock() - incremento);
		tareamaterialrepo.save(entidad);
		materialrepo.save(material);

		return tareamaterialmapper.toDto(entidad);
	}

	public TareaMaterialDTO updateRegistro(TareaMaterialDTO dto) {

		TareaEntity tarea = tarearepo.findById(dto.getId_tarea())
				.orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
		MaterialEntity material = materialrepo.findById(dto.getId_material())
				.orElseThrow(() -> new RuntimeException("Material no encontrado"));

		TareasMaterialId pk = new TareasMaterialId(tarea.getId(), material.getId());

		TareaMaterialEntity entidad = tareamaterialrepo.findById(pk)
				.orElseThrow(() -> new RuntimeException("No existe el registro para actualizar"));

		int cantidadActual = entidad.getCantidad();
		int cantidadNueva = dto.getCantidad();
		int diferencia = cantidadNueva - cantidadActual;

		if (diferencia > 0 && material.getStock() - diferencia < 0) {
			throw new RuntimeException("No hay stock suficiente");
		}

		material.setStock(material.getStock() - diferencia);

		entidad.setCantidad(cantidadNueva);

		tareamaterialrepo.save(entidad);
		materialrepo.save(material);

		return tareamaterialmapper.toDto(entidad);
	}

	public void deleteRegistro(Long id_tarea, Long id_material) {
		TareaEntity tarea = tarearepo.findById(id_tarea).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
		MaterialEntity material = materialrepo.findById(id_material)
				.orElseThrow(() -> new RuntimeException("Material no encontrado"));

		TareasMaterialId pk = new TareasMaterialId(tarea.getId(), material.getId());

		TareaMaterialEntity entidad = tareamaterialrepo.findById(pk)
				.orElseThrow(() -> new RuntimeException("No existe el registro para eliminar"));

		tareamaterialrepo.delete(entidad);

	}

	public Page<TareaMaterialDTO> search(TareaMaterialDTO filtro, Pageable pageable) {
		return tareamaterialrepo.findByFiltros(
				filtro.getId_tarea(), 
				filtro.getId_material(), 
				filtro.getCantidad(), 
				pageable)
				.map(tareamaterialmapper::toDto);
	}
	
	@Transactional(readOnly = true)
	public List<TareaMaterialDTO> findByTarea(Long idTarea) {
	    List<TareaMaterialEntity> entidades = tareamaterialrepo.findByTarea(idTarea);
	    
	    for (TareaMaterialEntity tm : entidades) {
	        System.out.println("=== DEBUG ===");
	        System.out.println("ID embeddable: " + tm.getId());
	        System.out.println("id_material entity: " + tm.getId_material());
	        if (tm.getId_material() != null) {
	            System.out.println("nombre: " + tm.getId_material().getNombre());
	        } else {
	            System.out.println("id_material ES NULL");
	        }
	    }
	    
	    return entidades.stream()
	            .map(tareamaterialmapper::toDto)
	            .toList();
	}
}
