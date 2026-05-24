package com.example.springMenuRivera.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity

@DiscriminatorValue("PlatoFuerte")

public class PlatoFuerte extends Alimento {

    public PlatoFuerte() {
    }

    public PlatoFuerte(String nombre, double precio) {
        super(nombre, precio);
    }

}
