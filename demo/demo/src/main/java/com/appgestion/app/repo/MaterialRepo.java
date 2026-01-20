package com.appgestion.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appgestion.app.model.MaterialEntity;

public interface MaterialRepo extends JpaRepository<MaterialEntity,Long>{

}
