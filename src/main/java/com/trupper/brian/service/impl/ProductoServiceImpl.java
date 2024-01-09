package com.trupper.brian.service.impl;

import com.trupper.brian.entity.Producto;
import com.trupper.brian.repository.ProductoRepository;
import com.trupper.brian.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }
}
