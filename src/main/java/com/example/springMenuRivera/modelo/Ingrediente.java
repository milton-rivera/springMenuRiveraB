package com.example.springMenuRivera.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "ingrediente_Stock")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cantidad_stock")
    private int cantidadStock;

    private String descripcion;

    public Ingrediente() {
    }

    public Ingrediente(int cantidadStock, String descripcion) {
        this.cantidadStock = cantidadStock;
        this.descripcion = descripcion;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
