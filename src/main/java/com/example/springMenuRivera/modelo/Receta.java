package com.example.springMenuRivera.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_chef")
    private Chef nombreChef;
    private String nombreReceta;

    @ManyToMany
    @JoinTable(
            name = "receta_ingredientes",


            joinColumns = @JoinColumn(name = "id_receta"),


            inverseJoinColumns = @JoinColumn(name = "id_ingrediente_stock")
    )
    private List<Ingrediente> ingredientes;

    @Column(columnDefinition = "TEXT")
    private String descripcionProceso;

    public Receta(Chef nombreChef, String nombreReceta, String descripcionProceso) {
        this.nombreChef = nombreChef;
        this.nombreReceta = nombreReceta;
        this.descripcionProceso = descripcionProceso;
        this.ingredientes= new ArrayList();
    }

    public Receta(Chef nombreChef, String nombreReceta, ArrayList<Ingrediente> ingredientes) {
        this.nombreChef = nombreChef;
        this.nombreReceta = nombreReceta;
        this.ingredientes = ingredientes;
    }



    public Receta() {
        this.ingredientes= new ArrayList();
    }

    public Chef getNombreChef() {
        return nombreChef;
    }

    public void setNombreChef(Chef nombreChef) {
        this.nombreChef = nombreChef;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    // Actualizalos para que reciban y devuelvan List
    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void addIngredientes(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    public String getDescripcionProceso() {
        return descripcionProceso;
    }

    public void setDescripcionProceso(String descripcionProceso) {
        this.descripcionProceso = descripcionProceso;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setChef(Chef nombreChef) {
        this.nombreChef = nombreChef;
    }





}
