package com.sistemafarmacia.sistemafarmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemafarmacia.sistemafarmacia.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    boolean existsByNit(String nit);
}
