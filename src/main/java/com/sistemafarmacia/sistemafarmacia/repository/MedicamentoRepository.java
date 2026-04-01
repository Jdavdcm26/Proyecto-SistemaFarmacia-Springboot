package com.sistemafarmacia.sistemafarmacia.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemafarmacia.sistemafarmacia.model.Medicamento;
import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, String> {
    List<Medicamento> findByNombreContainingIgnoreCase(String nombre);
    List<Medicamento> findByStockLessThanEqual(Integer stock);
    List<Medicamento> findByCategoriaId(Long categoriaId);
    List<Medicamento> findByFechaVencimientoBefore(LocalDate fecha);        
}
