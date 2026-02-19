package com.appgestion.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaValidarDTO {
	private Long id;
	private String nombre;
	private Long id_proyecto;
}
