package com.appgestion.app.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Tareas")
public class TareaEntity {
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
	
	@Column (name= "horas_estimadas")
	private int horas_estimadas;
	
	@Column (name= "obseraciones")
	private String observaciones;
	
	@Column (name= "tipo")
	private String tipo;
	
    @ManyToOne
    @JoinColumn(name = "id_proyecto", insertable = true, updatable = true, nullable = false)
	private ProyectoEntity id_proyecto;
    

    @ManyToOne
    @JoinColumn(name = "tareaPadre", nullable = true)
    @ToString.Exclude
    @JsonBackReference
    private TareaEntity tareaPadre;

    @OneToMany(mappedBy = "tareaPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
    private Set<TareaEntity> subtareas = new HashSet<>();
    
    @OneToMany(mappedBy = "id_tarea", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TareaMaterialEntity> materiales = new HashSet<>();
    
    @OneToMany(mappedBy = "id_tarea", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RegistroJornadaEntity> jornada = new HashSet<>();

}
