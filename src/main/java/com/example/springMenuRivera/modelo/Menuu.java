package com.example.springMenuRivera.modelo;

import java.util.ArrayList;

public class Menuu {
    private Gerente gerente;
    private ArrayList<Alimento> alimentos = new ArrayList<>();
    private ArrayList<Receta> recetas = new ArrayList<>();

    public Menuu() {
    }

    public Menuu(Gerente gerente) {
        this.gerente = gerente;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    public void addAlimentos(Alimento alimento) {
        this.alimentos.add(alimento);
    }

    public ArrayList<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(ArrayList<Receta> recetas) {
        this.recetas = recetas;
    }
}