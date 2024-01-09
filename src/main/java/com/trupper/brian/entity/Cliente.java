package com.trupper.brian.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "CLIENTES")
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "idCliente")
    private Long idCliente;

    @NotBlank
    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "activo", columnDefinition = "BIT")
    private Boolean activo;
}
