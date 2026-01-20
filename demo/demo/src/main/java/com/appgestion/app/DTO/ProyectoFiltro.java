package com.appgestion.app.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoFiltro {

	private String texto;
	private String estado;
	private LocalDate fecha_ini_desde;
	private LocalDate fecha_ini_hasta;
	private LocalDate fecha_fin_desde;
	private LocalDate fecha_fin_hasta;
	private Float margen_beneficio_min;
}
