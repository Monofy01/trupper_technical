package com.trupper.brian.repository;

import com.trupper.brian.entity.Cliente;
import com.trupper.brian.entity.ListaCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListaCompraRepository extends JpaRepository<ListaCompra, Long> {

    List<ListaCompra> findListaCompraByCustomerId(long id);
}
