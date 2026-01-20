package com.appgestion.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MaterialFiltro {

	private String texto;
	private Long stock_maximo;
	private float precio_maximo;
	private float precio_minimo;
}
