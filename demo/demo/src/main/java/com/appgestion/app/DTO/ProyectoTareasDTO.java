package com.appgestion.app.DTO;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoTareasDTO {
	public Long id;
    private String descripcion;
    private String estado;
    private LocalDate fecha_ini;
    private LocalDate fecha_fin;
    private float margen_beneficio;
    private String nombre;
    
    private List <TareaLazyDTO> tareas_principales;
}
