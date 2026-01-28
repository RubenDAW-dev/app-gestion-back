package com.appgestion.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appgestion.app.model.TareaMaterialEntity;
import com.appgestion.app.model.TareasMaterialId;

public interface TareaMaterialRepo extends JpaRepository<TareaMaterialEntity, TareasMaterialId> {

	@Query("""
			    SELECT tm
			    FROM TareaMaterialEntity tm
			    WHERE (:id_tarea IS NULL OR :id_tarea = 0 OR tm.id.id_tarea = :id_tarea)
			      AND (:id_material IS NULL OR :id_material = 0 OR tm.id.id_material = :id_material)
			      AND (:cantidad IS NULL OR :cantidad = 0 OR tm.cantidad > :cantidad)
			""")
	Page<TareaMaterialEntity> findByFiltros(@Param("id_tarea") Long id_tarea, @Param("id_material") Long id_material,
			@Param("cantidad") int cantidad, Pageable pageable);

}
