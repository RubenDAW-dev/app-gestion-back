package com.appgestion.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrabajadorDTO {

	private String nombre;
	private String apellido;
	private String DNI;
	private Long id_categoria;
	private String estado;
	private boolean rol;
}
