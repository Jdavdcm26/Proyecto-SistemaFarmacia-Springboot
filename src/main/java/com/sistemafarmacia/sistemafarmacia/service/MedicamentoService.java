
package com.sistemafarmacia.sistemafarmacia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistemafarmacia.sistemafarmacia.model.Medicamento;
import com.sistemafarmacia.sistemafarmacia.repository.MedicamentoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository repository;

    public Medicamento guardar(Medicamento medicamento) {
        return repository.save(medicamento);
    }

    public List<Medicamento> listarTodos() {
        return repository.findAll();
    }

    public Optional<Medicamento> buscarPorCodigo(String codigo) {
        return repository.findById(codigo);
    }

    public List<Medicamento> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Medicamento> buscarPorCategoria(Long categoriaId) {
        return repository.findByCategoriaId(categoriaId);
    }

    public void eliminar(String codigo) {
        repository.deleteById(codigo);
    }

    public boolean existe(String codigo) {
        return repository.existsById(codigo);
    }

    // Medicamentos con stock bajo o igual al minimo
    public List<Medicamento> alertasStockBajo() {
        return repository.findAll().stream()
                .filter(m -> m.getStock() <= m.getStockMinimo())
                .toList();
    }

    // Medicamentos que vencen en los proximos 30 dias
    public List<Medicamento> proximosAVencer() {
        return repository.findByFechaVencimientoBefore(LocalDate.now().plusDays(30));
    }

    // Actualizar stock al vender
    public void descontarStock(String codigo, Integer cantidad) {
        repository.findById(codigo).ifPresent(m -> {
            m.setStock(m.getStock() - cantidad);
            repository.save(m);
        });
    }

    // Actualizar stock al comprar
    public void aumentarStock(String codigo, Integer cantidad) {
        repository.findById(codigo).ifPresent(m -> {
            m.setStock(m.getStock() + cantidad);
            repository.save(m);
        });
    }
}    

