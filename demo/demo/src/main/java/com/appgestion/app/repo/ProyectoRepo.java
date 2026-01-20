package com.appgestion.app.repo;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appgestion.app.DTO.ProyectoAllDTO;
import com.appgestion.app.model.ProyectoEntity;

public interface ProyectoRepo extends JpaRepository<ProyectoEntity, Long> {
	@Query("SELECT p FROM ProyectoEntity p WHERE LOWER(p.nombre) LIKE LOWER(concat('%', :nombre,'%'))")
    Page<ProyectoEntity> findByNombreContainingIgnoreCase(@Param("nombre") String nombre, Pageable pageable);

	
    @Query("""
            SELECT p 
            FROM ProyectoEntity p
            WHERE
                (:texto IS NULL OR :texto = '' OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :texto, '%')))
            AND
                (:estado IS NULL OR :estado = '' OR p.estado = :estado)
            AND
                (:fecha_ini_desde IS NULL OR p.fecha_ini >= :fecha_ini_desde)
            AND
                (:fecha_ini_hasta IS NULL OR p.fecha_ini <= :fecha_ini_hasta)
            AND
                (:fecha_fin_desde IS NULL OR p.fecha_fin >= :fecha_fin_desde)
            AND
                (:fecha_fin_hasta IS NULL OR p.fecha_fin <= :fecha_fin_hasta)
            AND
            (:margen_beneficio_min IS NULL OR p.margen_beneficio >= :margen_beneficio_min)
        """)
    
        Page<ProyectoEntity> findByFiltros(
            @Param("texto") String texto,
            @Param("estado") String estado,
            @Param("fecha_ini_desde") LocalDate fecha_ini_desde,
            @Param("fecha_ini_hasta") LocalDate fecha_ini_hasta,
            @Param("fecha_fin_desde") LocalDate fecha_fin_desde,
            @Param("fecha_fin_hasta") LocalDate fecha_fin_hasta,
            @Param("margen_beneficio_min") Float margen_beneficio_min,
            Pageable pageable
        );
}
