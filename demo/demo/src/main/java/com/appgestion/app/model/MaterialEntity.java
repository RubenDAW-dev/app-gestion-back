package com.appgestion.app.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Materiales")
public class MaterialEntity {
	@Id
	@Column(nullable= false, updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column (name= "referencia")
	private String referencia;
	
	@Column (name= "numero_factura")
	private Long numero_factura;
	
	@Column (name= "precio_unitario")
	private float precio_unitario;
	
	@Column (name= "stock")
	private int stock;
	
    @OneToMany(mappedBy = "id_material", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TareaMaterialEntity> tareas = new HashSet<>();
}