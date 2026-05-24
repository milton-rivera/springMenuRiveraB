package com.example.springMenuRivera.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Bebida")
public class Bebida extends Alimento {

    public Bebida() {
    }

    public Bebida(String nombre, double precio) {
        super(nombre, precio);
    }

}