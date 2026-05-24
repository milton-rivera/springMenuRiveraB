package com.example.springMenuRivera.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Adicionales")
public class Adicionales_1 extends Alimento {

    public Adicionales_1() {
    }

    public Adicionales_1(String nombre, double precio) {
        super(nombre, precio);
    }

}
