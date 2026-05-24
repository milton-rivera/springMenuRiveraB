package com.example.springMenuRivera.repository;

import com.example.springMenuRivera.modelo.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
    // Acá también, vacío. Ya tenés todo lo necesario para traer los alimentos.
}