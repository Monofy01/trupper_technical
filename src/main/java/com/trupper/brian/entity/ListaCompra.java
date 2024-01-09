package com.trupper.brian.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "LISTAS_COMPRAS")
@Data
@NoArgsConstructor
public class ListaCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLista")
    private Long idLista;

    @NonNull
    @Column(name = "customerId")
    private Long customerId;
    @NotBlank
    @Column(name = "nombre", length = 50)
    private String nombre;
    @Column(name = "fechaRegistro", columnDefinition = "DATE")
    private LocalDateTime fechaRegistro;
    @Column(name = "fechaUltimaActualizacion", columnDefinition = "DATE")
    private LocalDateTime fechaUltimaActualizacion;
    @Column(name = "activo", columnDefinition = "BIT")
    private Boolean activo;

    public ListaCompra(long customerId, String name, Boolean activo) {
        this.customerId = customerId;
        this.nombre = name;
        this.fechaRegistro = LocalDateTime.now();;
        this.fechaUltimaActualizacion = LocalDateTime.now();;
        this.activo = activo;
    }
}
