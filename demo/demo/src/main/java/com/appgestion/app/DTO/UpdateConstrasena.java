package com.appgestion.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateConstrasena {
    private Long idUsuario;
    private String nuevaContrasena;
}
	