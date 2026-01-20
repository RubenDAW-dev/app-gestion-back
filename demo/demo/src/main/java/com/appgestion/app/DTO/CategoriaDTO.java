package com.appgestion.app.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
    private String nombre;

    @JsonProperty("precio_hora_trabajador")
    private float precioHoraTrabajador;

    @JsonProperty("precio_hora_coste")
    private float precioHoraCoste;
}
