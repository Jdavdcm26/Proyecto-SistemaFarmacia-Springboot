package com.sistemafarmacia.sistemafarmacia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistemafarmacia.sistemafarmacia.model.Proveedor;
import com.sistemafarmacia.sistemafarmacia.repository.ProveedorRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository repository;

    public Proveedor guardar(Proveedor proveedor) {
        return repository.save(proveedor);
    }

    public List<Proveedor> listarTodos() {
        return repository.findAll();
    }

    public Optional<Proveedor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public boolean existeNit(String nit) {
        return repository.existsByNit(nit);
    }
}