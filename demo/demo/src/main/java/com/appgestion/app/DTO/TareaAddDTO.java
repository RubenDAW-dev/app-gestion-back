package com.appgestion.app.DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaAddDTO {
	private String descripcion;
	private String estado;
	private LocalDate fecha_fin;
	private LocalDate fecha_ini;
	private int horas_estimadas;
	private String nombre;
	private String observaciones;
	private String tipo;
	private Long id_proyecto;
	private Long tarea_padre;
}
