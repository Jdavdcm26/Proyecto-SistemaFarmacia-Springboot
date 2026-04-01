package com.sistemafarmacia.sistemafarmacia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistemafarmacia.sistemafarmacia.model.Cliente;
import com.sistemafarmacia.sistemafarmacia.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente guardar(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Cliente> buscarPorId(String identificacion) {
        return repository.findById(identificacion);
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }

    public void eliminar(String identificacion) {
        repository.deleteById(identificacion);
    }

    public boolean existe(String identificacion) {
        return repository.existsById(identificacion);
    }
}
