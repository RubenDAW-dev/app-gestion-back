package com.appgestion.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrabajadorUsuarioDTO {
	private Long id;
	private String nombre;
	private String apellido;
	private String DNI;
	private CategoriaAllDTO id_categoria;
	private String estado;
}
