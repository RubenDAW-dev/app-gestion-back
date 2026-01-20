package com.appgestion.app.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Proyectos")
public class ProyectoEntity {
	@Id
	@Column(nullable= false, updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column (name= "descripcion")
	private String descripcion;
	
	@Column (name= "fecha_ini")
	private LocalDate fecha_ini;
	
	@Column (name= "fecha_fin")
	private LocalDate fecha_fin;
	
	@Column (name= "estado")
	private String estado;

	@Column (name= "margen_beneficio")
	private float margen_beneficio;
	
    @OneToMany(mappedBy = "id_proyecto")
    private Set<TareaEntity> tareas = new HashSet<>();
}
