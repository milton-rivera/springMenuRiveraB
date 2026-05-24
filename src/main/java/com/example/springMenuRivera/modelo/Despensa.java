package com.example.springMenuRivera.modelo;

import java.util.ArrayList;

public class Despensa {
    private Gerente gerente;
    private ArrayList<Ingrediente> ingredientes= new ArrayList();

    public Despensa() {
    }

    public Despensa(Gerente gerente) {
        this.gerente = gerente;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void addIngredientes(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
    }


}
