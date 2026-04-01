package com.sistemafarmacia.sistemafarmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemafarmacia.sistemafarmacia.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
