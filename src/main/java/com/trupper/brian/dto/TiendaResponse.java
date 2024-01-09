package com.trupper.brian.dto;

import lombok.Data;

import java.util.List;

@Data
public class TiendaResponse {
    private long cliente;
    private String nombre;
    private List<Detalle> detalles;
}
