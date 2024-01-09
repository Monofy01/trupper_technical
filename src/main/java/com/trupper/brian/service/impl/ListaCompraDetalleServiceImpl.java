package com.trupper.brian.service.impl;

import com.trupper.brian.dto.Detalle;
import com.trupper.brian.entity.*;
import com.trupper.brian.repository.ListaDetalleRepository;
import com.trupper.brian.repository.ProductoRepository;
import com.trupper.brian.service.DetallesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListaCompraDetalleServiceImpl implements DetallesService {

    @Autowired
    private ListaDetalleRepository detalleRepository;
    @Autowired
    private ProductoRepository productoRepository;


    @Override
    @Transactional
    public List<ListaCompraDetalle> saveAll(long idListaCompra, List<Detalle> listaDetalles) {

        // Se itera sobre los detalles

        // DETALLE =
        // idProducto:2000
        // cantidad:5

        listaDetalles.forEach(detalle -> {
            // Se busca que el producto del detalle si exista
            Optional<Producto> productoEncontrado = productoRepository.findById(detalle.getIdProducto());
            if (productoEncontrado.isEmpty()) {
                throw new RuntimeException("El producto con ID: " + detalle.getIdProducto() + " no ha sido encontrado");
            }
        });

        // Lista para almacenar los detalles creados
        List<ListaCompraDetalle> listaDetallesCreados = new ArrayList<>();

        // Se guardan esos detalles de forma individual con el id de la lista de compra
        listaDetalles.forEach(detalle -> {
            ListaCompraDetalle listaCompraDetalle = detalleRepository.save(
                    new ListaCompraDetalle(
                            new ListaCompraDetallePK(idListaCompra, detalle.getIdProducto()),
                            detalle.getCantidad()
                    )
            );
            listaDetallesCreados.add(listaCompraDetalle);
        });

        return listaDetallesCreados;

    }
}
