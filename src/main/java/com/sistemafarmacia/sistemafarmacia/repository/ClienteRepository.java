package com.sistemafarmacia.sistemafarmacia.repository;

import com.sistemafarmacia.sistemafarmacia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;   
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findByIdentificacionContainingIgnoreCase(String identificacion);
}
