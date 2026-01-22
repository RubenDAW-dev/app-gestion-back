package com.appgestion.app.repo;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appgestion.app.model.TareaEntity;

public interface TareaRepo extends JpaRepository<TareaEntity, Long> {
	@EntityGraph(attributePaths = { "id_proyecto" })
	Page<TareaEntity> findAll(Pageable pageable);

	@Query("""
		    SELECT t
		    FROM TareaEntity t
		    WHERE
		        (:term IS NULL OR :term = '' OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :term, '%')))
		    AND
		        (:tipo IS NULL OR :tipo = '' OR t.tipo = :tipo)
		    AND
		        (:estado IS NULL OR :estado = '' OR t.estado = :estado)
		    AND
		        (:fecha_inicio_desde IS NULL OR t.fecha_ini >= :fecha_inicio_desde)
		    AND
		        (:fecha_inicio_hasta IS NULL OR t.fecha_ini <= :fecha_inicio_hasta)
		    AND
		        (:fecha_fin_desde IS NULL OR t.fecha_fin >= :fecha_fin_desde)
		    AND
		        (:fecha_fin_hasta IS NULL OR t.fecha_fin <= :fecha_fin_hasta)
		    AND
		        (:padre IS NULL OR :padre = 0 OR t.tareaPadre.id = :padre)
		    AND
		        (:proyecto IS NULL OR :proyecto = 0 OR t.id_proyecto.id = :proyecto)
		""")
	@EntityGraph(attributePaths = { "id_proyecto" })
	Page<TareaEntity> findByFiltros(
			@Param("term") String term, 
			@Param("tipo") String tipo,
			@Param("estado") String estado, 
			@Param("fecha_inicio_desde") LocalDate fecha_inicio_desde,
			@Param("fecha_inicio_hasta") LocalDate fecha_inicio_hasta,
			@Param("fecha_fin_desde") LocalDate fecha_fin_desde, 
			@Param("fecha_fin_hasta") LocalDate fecha_fin_hasta,
			@Param("padre") Long padre, @Param("proyecto") Long proyecto, Pageable pageable);

}
