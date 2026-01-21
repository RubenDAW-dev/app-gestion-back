package com.appgestion.app.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TareaTablaDTO {
	private String nombre;
	private String estado;
	private LocalDate fecha_ini;
	private LocalDate fecha_fin;
	private String tipo;
	private int horas_estimadas;

}
