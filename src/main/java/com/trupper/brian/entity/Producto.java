package com.trupper.brian.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PRODUCTOS")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Long idProducto;

    @Column(name = "clave", length = 15)
    private String clave;
    @Column(name = "descripcion", length = 150)
    private String descripcion;
    @Column(name = "activo", columnDefinition = "BIT")
    private Boolean activo;

}
