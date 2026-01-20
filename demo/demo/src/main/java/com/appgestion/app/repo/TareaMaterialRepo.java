package com.appgestion.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appgestion.app.model.TareaMaterialEntity;
import com.appgestion.app.model.TareasMaterialId;

public interface TareaMaterialRepo extends JpaRepository<TareaMaterialEntity, TareasMaterialId> {

}
