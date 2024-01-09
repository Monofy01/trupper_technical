package com.trupper.brian.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListasComprasDTO {
    private Long cliente;
    private String nombre;
    private List<Detalle> detalles;
}
