package com.sistemafarmacia.sistemafarmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemafarmacia.sistemafarmacia.model.Factura; 
import java.util.Optional;
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    Optional<Factura> findByVentaId(Long ventaId);
    Optional<Factura> findTopByOrderByIdDesc();    
}
