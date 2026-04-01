package com.sistemafarmacia.sistemafarmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemafarmacia.sistemafarmacia.model.DetalleVenta;
import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    List<DetalleVenta> findByVentaId(Long ventaId);    
}
