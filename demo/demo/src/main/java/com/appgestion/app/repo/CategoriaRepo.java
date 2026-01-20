package com.appgestion.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appgestion.app.model.CategoriaEntity;

public interface CategoriaRepo extends JpaRepository<CategoriaEntity, Long> {
	@Query("SELECT c FROM CategoriaEntity c WHERE LOWER(c.nombre) LIKE LOWER(concat('%', :nombre,'%'))")
    Page<CategoriaEntity> findByNombreContainingIgnoreCase(@Param("nombre") String nombre, Pageable pageable);

}