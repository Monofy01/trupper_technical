package com.trupper.brian.service.impl;

import com.trupper.brian.dto.ListasComprasDTO;
import com.trupper.brian.entity.ListaCompra;
import com.trupper.brian.entity.ListaCompraDetalle;
import com.trupper.brian.entity.ListaCompraDetallePK;
import com.trupper.brian.repository.ListaDetalleRepository;
import com.trupper.brian.repository.ListaCompraRepository;
import com.trupper.brian.service.DetallesService;
import com.trupper.brian.service.ListaCompraService;
import com.trupper.brian.service.TiendaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TiendaServiceImpl implements TiendaService {

    @Autowired
    private ListaCompraService listaCompraService;
    @Autowired
    private DetallesService detallesService;


    @Autowired
    private ListaDetalleRepository detalleRepository;
    @Autowired
    private ListaCompraRepository listaCompraRepository;


    @Transactional
    @Override
    public List<ListasComprasDTO> crearListasCompras(List<ListasComprasDTO> request) {
        // Iteramos sobre las listas de compras
        request.forEach(
                listaComprasItem -> {
                    // Para cada lista generamos un objeto que sera guardado en tabla LISTAS_COMPRAS
                    ListaCompra listaComprasCore = new ListaCompra(
                            listaComprasItem.getCliente(),
                            listaComprasItem.getNombre(),
                            true
                    );
                    // Se realiza la operacion de save
                    listaCompraService.save(listaComprasCore);

                    // Una vez generada la lista global, le guardamos los detalles a esa lista
                    detallesService.saveAll(
                            listaComprasCore.getIdLista(),
                            listaComprasItem.getDetalles()
                    );
                }
        );

        return request;
    }

    @Override
    public List<ListaCompra> obtenerListaClienteId(Long clienteId) {
        return listaCompraRepository.findListaCompraByCustomerId(clienteId);
    }

    @Override
    public ListasComprasDTO actualizarListaClienteId(ListasComprasDTO comprasDTO, Long listaId) {

        // Se busca todas las listas de compras que tienen un cliente en común
        List<ListaCompra> listasEncontradas = listaCompraRepository.findListaCompraByCustomerId(comprasDTO.getCliente());
        if (listasEncontradas.isEmpty())
            throw new RuntimeException("La lista de compras proporcionada para actualizar no existe");

        // Para cada lista encontrada se debe verificar que el Detalle que se quiere actualizar existe o no
        listasEncontradas.forEach(listaCompra -> {

            // Solo realiza el proceso de update para la Lista de Precio que coincida
            if (listaCompra.getIdLista().equals(listaId)) {
                // Actualizamos la tabla global de LISTA_COMPRAS
                listaCompra.setNombre(comprasDTO.getNombre()); // Nuevo nombre ingresado por DTO
                listaCompra.setFechaUltimaActualizacion(LocalDateTime.now()); // Actualizacion de campo

                // Se deben obtener los detalles de la lista de compras actual
                List<ListaCompraDetalle> listaDetalles = detalleRepository.findById_IdListaCompra(listaCompra.getIdLista());
                if (listaDetalles.isEmpty())
                    throw new RuntimeException("Los detalles de la lista de compras para actualizar no existen");


                // Obtenemos los detalles donde los valores hicieron match para realizar actualizacion y para realizar insercion
                List<ListaCompraDetalle> detallesExistentes = listaDetalles.stream().peek(detalleOriginal -> comprasDTO.getDetalles()
                        .stream()
                        .filter(detalleNuevo -> detalleOriginal.getId().getIdCodigoProducto().equals(detalleNuevo.getIdProducto()))
                        .forEach(detalleActualizar -> detalleOriginal.setCantidad(detalleActualizar.getCantidad()))).toList();

                List<ListaCompraDetalle> detallesInexistentes = comprasDTO.getDetalles().stream()
                        .filter(detalleNuevo -> listaDetalles.stream()
                                .noneMatch(detalleOriginal -> detalleOriginal.getId().getIdCodigoProducto().equals(detalleNuevo.getIdProducto())))
                        .map(detalleNuevo -> new ListaCompraDetalle(
                                new ListaCompraDetallePK(listaCompra.getIdLista(), detalleNuevo.getIdProducto()),
                                detalleNuevo.getCantidad()))
                        .toList();

                detalleRepository.saveAll(detallesExistentes);
                detalleRepository.saveAll(detallesInexistentes);

                // Se guardan solo la Lista de Compras Global
                listaCompraRepository.save(listaCompra);
            }
        });
        return comprasDTO;
    }

    @Override
    public Boolean borrarListaId(long id) {
        try {
            List<ListaCompraDetalle> detalleDelete = detalleRepository.findById_IdListaCompra(id);
            if (!detalleDelete.isEmpty()) {
                detalleRepository.deleteAll(detalleDelete);
                listaCompraRepository.deleteById(id);
            }
            listaCompraRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
