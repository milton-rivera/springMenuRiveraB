package com.example.springMenuRivera.repository;


import com.example.springMenuRivera.modelo.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Integer> {
    // ¡Vacío! Al heredar de JpaRepository ya trae métodos como findAll(), save(), findById(), etc.
}