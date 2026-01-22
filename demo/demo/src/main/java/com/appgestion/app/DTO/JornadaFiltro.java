package com.appgestion.app.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JornadaFiltro {
	public LocalDate fecha_desde;
	public LocalDate fecha_hasta;
	public boolean validado;
}
