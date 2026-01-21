package com.appgestion.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @Column(name = "id_trabajador")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_trabajador", foreignKey = @ForeignKey(name = "fk_usuarios_trabajadores"))
    private TrabajadorEntity trabajador;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "rol")
    private boolean rol;
    
    @Column(name = "cambio_contrasena")
    private boolean cambio_contrasena;
}
