package com.appgestion.app.DTO;

import com.appgestion.app.model.MaterialEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialAgrupadoDTO {
	MaterialDTO material;
	Long cantidadTotal;
	Double precioTotal;

	public MaterialAgrupadoDTO(MaterialEntity m, Long cantidadTotal, Double precioTotal) {
		this.material = new MaterialDTO(m.getNombre(), m.getReferencia(), m.getNumero_factura(), m.getPrecio_unitario(),
				m.getStock());

		this.cantidadTotal = cantidadTotal;
		this.precioTotal = precioTotal;
	}
}