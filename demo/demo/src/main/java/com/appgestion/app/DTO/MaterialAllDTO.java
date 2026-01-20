package com.appgestion.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialAllDTO {
	private Long id;
	private String nombre;
	
	private String referencia;
	
	private int numero_factura;

	private float precio_unitario;	

	private int stock;
}
