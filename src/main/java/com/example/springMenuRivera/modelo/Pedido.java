package com.example.springMenuRivera.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Pedido {
    private Cliente cliente;
    private ArrayList<Mesero> meseros= new ArrayList();
    private ArrayList<Alimento> alimentosAdquiridos= new ArrayList();
    private ArrayList<Cliente> clientes= new ArrayList();
    private LocalDate fechaPedido;
    private LocalTime horaPedido;
    private double precioTotalPedido;
    private boolean estado;

    public Pedido() {
    }

    public Pedido(Cliente cliente, LocalDate fechaPedido, LocalTime horaPedido, double precioTotalPedido, boolean estado) {
        this.cliente = cliente;
        this.fechaPedido = fechaPedido;
        this.horaPedido = horaPedido;
        this.precioTotalPedido = precioTotalPedido;
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Mesero> getMeseros() {
        return meseros;
    }

    public void addMeseros(Mesero mesero) {
        this.meseros.add(mesero);
    }

    public ArrayList<Alimento> getAlimentosAdquiridos() {
        return alimentosAdquiridos;
    }

    public void addAlimentosAdquiridos(Alimento alimento) {
        this.alimentosAdquiridos.add(alimento);
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void addClientes(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public LocalTime getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(LocalTime horaPedido) {
        this.horaPedido = horaPedido;
    }

    public double getPrecioTotalPedido() {
        return precioTotalPedido;
    }

    public void setPrecioTotalPedido(double precioTotalPedido) {
        this.precioTotalPedido = precioTotalPedido;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


}
