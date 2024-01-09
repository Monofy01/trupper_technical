package com.trupper.brian.repository;

import com.trupper.brian.entity.Cliente;
import com.trupper.brian.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
