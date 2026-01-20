package com.appgestion.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appgestion.app.model.TrabajadorEntity;

public interface TrabajadorRepo extends JpaRepository<TrabajadorEntity, Long>{
	@Query("SELECT t FROM TrabajadorEntity t WHERE LOWER(t.nombre) LIKE LOWER(concat('%', :nombre,'%'))")
    Page<TrabajadorEntity> findByNombreContainingIgnoreCase(@Param("nombre") String nombre, Pageable pageable);

	@Query("""
		    SELECT t
		    FROM TrabajadorEntity t
		    WHERE
		        (:texto IS NULL OR :texto = '' OR 
		         LOWER(t.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR
		         LOWER(t.apellido) LIKE LOWER(CONCAT('%', :texto, '%')) OR
		         t.DNI LIKE CONCAT('%', :texto, '%'))
		    AND
		        (:estado IS NULL OR :estado = '' OR t.estado = :estado)
		    AND
		        (:idCategoria IS NULL OR :idCategoria = 0 OR t.categoria.id = :idCategoria)
		""")
		Page<TrabajadorEntity> findByFiltros(
		        @Param("texto") String texto,
		        @Param("estado") String estado,
		        @Param("idCategoria") Long idCategoria,
		        Pageable pageable);


    @EntityGraph(attributePaths = {"categoria", "usuario"})
    Page<TrabajadorEntity> findAll(Pageable pageable);
	
}
