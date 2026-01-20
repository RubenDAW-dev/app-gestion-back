package com.appgestion.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrabajadorFiltro {

	private String texto;
	private String estado;
	private int id_categoria;
	
}
