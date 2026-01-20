package com.appgestion.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appgestion.app.model.TareaEntity;

public interface TareaRepo extends JpaRepository<TareaEntity, Long>{

}
