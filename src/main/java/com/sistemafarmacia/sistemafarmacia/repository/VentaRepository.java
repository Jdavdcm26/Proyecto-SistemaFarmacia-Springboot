package com.sistemafarmacia.sistemafarmacia.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemafarmacia.sistemafarmacia.model.Venta;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long>{
   
    List<Venta> findByFechaBetween(LocalDate inicio, LocalDate fin);
    List<Venta> findByClienteIdentificacion(String identificacion);
    List<Venta> findByEstado(String estado);
    List<Venta> findByFecha(LocalDate fecha);
    Long countByFecha(LocalDate fecha);    
}
