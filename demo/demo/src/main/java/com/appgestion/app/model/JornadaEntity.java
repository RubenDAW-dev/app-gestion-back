package com.appgestion.app.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "RegistroJornada")
public class JornadaEntity {
	@Id
	@Column(nullable= false, updatable = false, name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "id_trabajador", insertable = true, updatable = true, nullable = false)
	private TrabajadorEntity id_trabajador;
    
    @ManyToOne
    @JoinColumn(name = "id_tarea", insertable = true, updatable = true, nullable = false)
	private TareaEntity id_tarea;
    
	@Column (name= "horas")
	private int horas;
    
	@Column (name= "fecha")
	private LocalDate fecha;
	
	@Column (name= "validado")
	private boolean validado;
}
