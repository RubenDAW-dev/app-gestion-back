package com.appgestion.app.DTO;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaJornadaDTO {
    private Long id;
    private String descripcion;
    private String estado;
    private LocalDate fecha_ini;
    private LocalDate fecha_fin;
    private int horas_estimadas;
    private String nombre;
    private String observaciones;
    private String tipo;
    private ProyectoDTOTarea id_proyecto;
    private TareaDTOTarea tarea_padre;

    private LocalDate fecha;
    private int horas;
    private boolean validado;
}
