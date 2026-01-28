package com.appgestion.app.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appgestion.app.model.JornadaEntity;

public interface JornadaRepo extends JpaRepository<JornadaEntity, Long> {
	@EntityGraph(attributePaths = { "id_tarea", "id_trabajador" })
	Page<JornadaEntity> findAll(Pageable pageable);

	@Query("""
			    SELECT j
			    FROM JornadaEntity j
			    WHERE
			        (:fecha_desde IS NULL OR j.fecha >= :fecha_desde)
			    AND
			        (:fecha_hasta IS NULL OR j.fecha <= :fecha_hasta)
			    AND
			        (:validado IS NULL OR j.validado = :validado)
			""")
	Page<JornadaEntity> findByFiltros(@Param("fecha_desde") LocalDate fechaDesde,
			@Param("fecha_hasta") LocalDate fechaHasta, @Param("validado") Boolean validado, Pageable pageable);

	long countByValidadoFalse();

	@EntityGraph(attributePaths = { "id_tarea", "id_trabajador" })
	Page<JornadaEntity> findByValidado(boolean validado, Pageable pageable);

	@Query("""
		    SELECT j FROM JornadaEntity j
		    JOIN FETCH j.id_tarea t
		    WHERE j.id_trabajador.id = :trabajadorId
		""")
		Page<JornadaEntity> findJornadasByTrabajador(@Param("trabajadorId") Long trabajadorId, Pageable pageable);

}
