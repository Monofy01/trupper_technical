package com.trupper.brian.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "LISTAS_COMPRAS_DETALLES")
@Data
@NoArgsConstructor
public class ListaCompraDetalle {

    @EmbeddedId
    private ListaCompraDetallePK id;

    @NonNull
    @Min(value = 1)
    @Column(name = "cantidad")
    private Integer cantidad;


    public ListaCompraDetalle(ListaCompraDetallePK id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }
}
