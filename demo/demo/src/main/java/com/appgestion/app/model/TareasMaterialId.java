package com.appgestion.app.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TareasMaterialId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id_tarea;
	private Long id_material;
}
