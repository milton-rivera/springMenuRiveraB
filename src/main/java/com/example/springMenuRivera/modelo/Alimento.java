package com.example.springMenuRivera.modelo;


import jakarta.persistence.*;

/**
 *
 * @author milton
 */
@Entity
@Table(name = "alimento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private double precio;

    @OneToOne
    @JoinColumn(name = "id_receta")
    private Receta receta;

    public Alimento() {
    }

    public Alimento(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
    @Transient
    private int idRecetaAsociada;


    public int getIdRecetaAsociada() {
        return idRecetaAsociada;
    }

    public void setIdRecetaAsociada(int idRecetaAsociada) {
        this.idRecetaAsociada = idRecetaAsociada;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
