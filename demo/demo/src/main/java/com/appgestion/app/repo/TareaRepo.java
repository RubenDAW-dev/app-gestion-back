package com.appgestion.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.appgestion.app.model.TareaEntity;

public interface TareaRepo extends JpaRepository<TareaEntity, Long>{
	@EntityGraph(attributePaths = {"id_proyecto"})
    Page<TareaEntity> findAll(Pageable pageable);


}
