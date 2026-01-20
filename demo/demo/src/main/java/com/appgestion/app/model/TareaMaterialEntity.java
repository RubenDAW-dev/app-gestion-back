package com.appgestion.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TareasMaterial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TareaMaterialEntity {

	@EmbeddedId
	private TareasMaterialId id;

	@ManyToOne
	@MapsId("id_tarea")
	@JoinColumn(name = "id_tarea")
	private TareaEntity id_tarea;

	@ManyToOne
	@MapsId("id_material")
	@JoinColumn(name = "id_material")
	private MaterialEntity id_material;

	@Column(name = "cantidad")
	private int cantidad;



}
