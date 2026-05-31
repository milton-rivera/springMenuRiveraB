package com.example.springMenuRivera.repository;


import com.example.springMenuRivera.modelo.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Integer> {

}