package com.trupper.brian.service;

import com.trupper.brian.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente save(Cliente cliente);

    List<Cliente> findAll();
    Optional<Cliente> findById(long id);
//    Cliente getListaCompras(Cliente cliente);
//    Cliente getListaPreciosCliente(Cliente cliente);
//    Cliente deleteListaPreciosCliente(Cliente cliente);
//


}
