package com.appgestion.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TareaMaterialDTO {
private Long id_tarea;
private Long id_material;
private int cantidad;
}
