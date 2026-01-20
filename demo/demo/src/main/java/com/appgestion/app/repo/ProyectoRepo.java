package com.appgestion.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.appgestion.app.model.ProyectoEntity;

public interface ProyectoRepo extends JpaRepository<ProyectoEntity, Long> {
	@Query("SELECT p FROM ProyectoEntity p WHERE LOWER(p.nombre) LIKE LOWER(concat('%', :nombre,'%'))")
    Page<ProyectoEntity> findByNombreContainingIgnoreCase(@Param("nombre") String nombre, Pageable pageable);
}
