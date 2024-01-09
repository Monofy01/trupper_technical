package com.trupper.brian.service;

import com.trupper.brian.dto.ListasComprasDTO;
import com.trupper.brian.entity.ListaCompra;

import java.util.List;

public interface TiendaService {

    List<ListasComprasDTO> crearListasCompras(List<ListasComprasDTO> request);

    List<ListaCompra> obtenerListaClienteId(Long clienteId);

    ListasComprasDTO actualizarListaClienteId(ListasComprasDTO nuevasCompras, Long listaId);

    Boolean borrarListaId(long id);
}
