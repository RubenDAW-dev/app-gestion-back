package com.appgestion.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appgestion.app.model.MaterialEntity;

public interface MaterialRepo extends JpaRepository<MaterialEntity,Long>{

	@Query("""
		    SELECT m
		    FROM MaterialEntity m
		    WHERE
		        (
		            :texto IS NULL OR :texto = '' OR
		            LOWER(m.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR
		            LOWER(m.referencia) LIKE LOWER(CONCAT('%', :texto, '%'))
		        )
		    AND
		        (
		            :stock_maximo IS NULL OR
		            :stock_maximo = 0 OR
		            m.stock <= :stock_maximo
		        )
		    AND
		        (
		            :precio_minimo IS NULL OR
		            :precio_minimo = 0 OR
		            m.precio_unitario >= :precio_minimo
		        )
		    AND
		        (
		            :precio_maximo IS NULL OR
		            :precio_maximo = 0 OR
		            m.precio_unitario <= :precio_maximo
		        )
		""")
		Page<MaterialEntity> findByFiltros(
		    @Param("texto") String texto,
		    @Param("stock_maximo") Long stock_maximo,
		    @Param("precio_minimo") Float precio_minimo,
		    @Param("precio_maximo") Float precio_maximo,
		    Pageable pageable
		);


}
