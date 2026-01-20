package com.appgestion.app.model;

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
@Table(name = "Categorias")
public class CategoriaEntity {
	@Id
	@Column(nullable= false, updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column (name= "precioHoraTrabajador")
	private float precioHoraTrabajador;
	
	@Column (name= "precioHoraCoste")
	private float precioHoraCoste;
	
    @OneToMany(mappedBy = "categoria")
    private Set<TrabajadorEntity> trabajadores = new HashSet<>();
    
}