package com.example.springMenuRivera.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Postres")
public class Postres extends Alimento {

    public Postres() {
    }

    public Postres(String nombre, double precio) {
        super(nombre, precio);
    }

}
