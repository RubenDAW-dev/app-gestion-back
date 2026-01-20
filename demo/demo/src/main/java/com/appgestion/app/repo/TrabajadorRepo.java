package com.appgestion.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.appgestion.app.DTO.TrabajadorFiltro;
import com.appgestion.app.model.TrabajadorEntity;

public interface TrabajadorRepo extends JpaRepository<TrabajadorEntity, Long>{
	@Query("SELECT t FROM TrabajadorEntity t WHERE LOWER(t.nombre) LIKE LOWER(concat('%', :nombre,'%'))")
    Page<TrabajadorEntity> findByNombreContainingIgnoreCase(@Param("nombre") String nombre, Pageable pageable);

	@Query("""
		    SELECT t
		    FROM TrabajadorEntity t
		    WHERE
		        (
		            :#{#filtro.texto} IS NULL OR
		            LOWER(t.nombre) LIKE LOWER(CONCAT('%', :#{#filtro.texto}, '%')) OR
		            LOWER(t.apellido) LIKE LOWER(CONCAT('%', :#{#filtro.texto}, '%')) OR
		            t.DNI LIKE CONCAT('%', :#{#filtro.texto}, '%')
		        )
		    AND
		        (
		            :#{#filtro.estado} IS NULL OR
		            :#{#filtro.estado} = '' OR
		            t.estado = :#{#filtro.estado}
		        )
		    AND
		        (
		            :#{#filtro.id_categoria} = 0 OR
		            t.categoria.id = :#{#filtro.id_categoria}
		        )
		    """)
	Page<TrabajadorEntity> findByFiltros(@Param("filtro") TrabajadorFiltro trabajadorfiltro,Pageable pageable);


	
}
