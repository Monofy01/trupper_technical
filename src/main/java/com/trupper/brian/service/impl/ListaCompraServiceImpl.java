package com.trupper.brian.service.impl;

import com.trupper.brian.entity.Cliente;
import com.trupper.brian.entity.ListaCompra;
import com.trupper.brian.repository.ListaCompraRepository;
import com.trupper.brian.service.ClienteService;
import com.trupper.brian.service.ListaCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListaCompraServiceImpl implements ListaCompraService {

    @Autowired
    private ListaCompraRepository compraRepository;

    @Autowired
    private ClienteService clienteService;

    @Override
    public ListaCompra save(ListaCompra listaCompra) {
        Optional<Cliente> clientFound = clienteService.findById(listaCompra.getCustomerId());
        if (clientFound.isEmpty())
            throw new RuntimeException("Customer ID is not present in Customer Table");
        return compraRepository.save(listaCompra);
    }
}
