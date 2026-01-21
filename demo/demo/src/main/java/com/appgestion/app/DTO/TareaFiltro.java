package com.appgestion.app.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TareaFiltro {
	private String term;
	private String tipo;
	private String estado;
	private LocalDate fecha_inicio_desde;
	private LocalDate fecha_inicio_hasta;
	private LocalDate fecha_fin_desde;
	private LocalDate fecha_fin_hasta;
	private Long padre;
	private Long proyecto;
}
