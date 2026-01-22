package com.appgestion.app.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JornadaDTO {
	private LocalDate fecha;
	private int horas;
	private boolean validado;
	private Long id_tarea;
	private Long id_trabajador;
}
