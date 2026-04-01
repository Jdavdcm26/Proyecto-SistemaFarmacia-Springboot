package com.sistemafarmacia.sistemafarmacia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistemafarmacia.sistemafarmacia.model.Categoria;
import com.sistemafarmacia.sistemafarmacia.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria guardar(Categoria categoria) {
        return repository.save(categoria);
    }

    public List<Categoria> listarTodas() {
        return repository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}