package com.appgestion.app.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appgestion.app.model.TrabajadorEntity;
import com.appgestion.app.model.UsuarioEntity;

public interface UsuarioRepo extends JpaRepository<UsuarioEntity, Long>{

	@Query("SELECT u FROM UsuarioEntity u WHERE LOWER(u.usuario) LIKE LOWER(concat('%', :usuario,'%'))")
	Page<UsuarioEntity> findByUsuario(@Param("usuario") String texto, Pageable pageable);

    Optional<UsuarioEntity> findByUsuario(String usuario);
}
