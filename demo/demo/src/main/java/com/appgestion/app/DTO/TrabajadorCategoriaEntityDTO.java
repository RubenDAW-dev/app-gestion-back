package com.appgestion.app.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrabajadorCategoriaEntityDTO {

	private Long id;
	private String nombre;
	private String apellido;
	private String DNI;
	private CategoriaAllDTO categoria;
	private String estado;
}
