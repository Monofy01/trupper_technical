package com.trupper.brian.repository;

import com.trupper.brian.entity.ListaCompraDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaDetalleRepository extends JpaRepository<ListaCompraDetalle, Long> {

    List<ListaCompraDetalle> findById_IdListaCompra(long listaCompraId);
}
