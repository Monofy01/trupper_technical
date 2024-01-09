package com.trupper.brian.service;

import com.trupper.brian.dto.Detalle;
import com.trupper.brian.entity.ListaCompraDetalle;

import java.util.List;

public interface DetallesService {

    List<ListaCompraDetalle> saveAll(long idListaCore, List<Detalle> detalles);
}
