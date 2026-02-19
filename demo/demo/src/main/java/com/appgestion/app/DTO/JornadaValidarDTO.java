package com.appgestion.app.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JornadaValidarDTO {
	private Long id;
	private LocalDate fecha;
	private int horas;
	private boolean validado;
	private TareaValidarDTO id_tarea;
	private TrabajadorNombreDTO id_trabajador;
}
