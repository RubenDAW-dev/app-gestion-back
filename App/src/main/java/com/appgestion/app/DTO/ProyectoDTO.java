package com.appgestion.app.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoDTO {

    private String descripcion;
    private String estado;
    private LocalDate fecha_ini;
    private LocalDate fecha_fin;
    private float margen_beneficio;
    private String nombre;
}
