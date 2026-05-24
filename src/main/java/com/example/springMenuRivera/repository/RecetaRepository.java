package com.example.springMenuRivera.repository;

import com.example.springMenuRivera.modelo.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    // Acá también, vacío. Ya tenés todo lo necesario para traer los alimentos.
}