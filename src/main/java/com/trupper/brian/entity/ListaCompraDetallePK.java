package com.trupper.brian.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListaCompraDetallePK implements Serializable {

    private Long idListaCompra;
    private Long idCodigoProducto;

}
