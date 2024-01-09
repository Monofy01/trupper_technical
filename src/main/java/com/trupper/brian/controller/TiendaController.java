package com.trupper.brian.controller;


import com.trupper.brian.dto.CustomResponse;
import com.trupper.brian.dto.ListasComprasDTO;
import com.trupper.brian.entity.ListaCompra;
import com.trupper.brian.service.TiendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/store")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    // Crea un servicio POST para guardar las siguientes listas de compras
    @PostMapping("/guardar")
    public ResponseEntity<CustomResponse> save(@Valid @RequestBody List<ListasComprasDTO> compras) {
        List<ListasComprasDTO> payload = tiendaService.crearListasCompras(compras);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse("Se han creado correctamente las Listas de Compras", true, payload));
    }

    // Crear servicio GET que Obtenga las listas de compras del cliente X
    @GetMapping("/obtener-lista/{clienteId}")
    public ResponseEntity<CustomResponse> getById(@PathVariable("clienteId") Long clienteId) {
        List<ListaCompra> payload = tiendaService.obtenerListaClienteId(clienteId);
        String msg = payload.isEmpty() ? "No se han encontrado listas de compras" : "Se han encontrado listas de compras exitosamente";
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(msg, true, payload));
    }

    // Crear Servicio PUT que actualice la lista de precios del cliente X
    @PutMapping("/actualizar-lista/{listaId}")
    public ResponseEntity<CustomResponse> update(@RequestBody ListasComprasDTO nuevasCompras, @PathVariable Long listaId) {
        ListasComprasDTO payload = tiendaService.actualizarListaClienteId(nuevasCompras, listaId);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse("Se ha actualizado correctamente la informacion", true, payload));
    }

    // Crear Servicio DELETE para eliminar cualquier lista de compras.
    @DeleteMapping("/borrar-lista/{id}")
    public ResponseEntity<CustomResponse> delete(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse("Se ha borrado correctamente la informacion", true, tiendaService.borrarListaId(id)));
    }


}
