package com.appgestion.app.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Trabajadores")
public class TrabajadorEntity {
	@Id
	@Column(nullable= false, updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column (name= "apellido")
	private String apellido;
	
	@Column (name= "DNI",unique = true)
	private String DNI;
	
	@ManyToOne
	@JoinColumn (name= "id_categoria", insertable = true, updatable = true, nullable = false)
	private CategoriaEntity categoria;
	
	@Column (name= "estado")
	private String estado;

    @OneToMany(mappedBy = "id_trabajador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RegistroJornadaEntity> jornada = new HashSet<>();
	

    @OneToOne(mappedBy = "trabajador", fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

}
