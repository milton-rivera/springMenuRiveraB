package com.example.springMenuRivera.repository;

import com.example.springMenuRivera.modelo.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepository extends JpaRepository<Chef, Integer> {

}