package com.example.springMenuRivera.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Mesero extends Empleado {
    private double Salario;

    public Mesero() {
    }

    public Mesero(double Salario, LocalDate fechaVinculacion, LocalTime horaIngreso, LocalTime horaSalida) {
        super(fechaVinculacion, horaIngreso, horaSalida);
        this.Salario = Salario;
    }

    public Mesero(LocalDate fechaVinculacion, LocalTime horaIngreso, LocalTime horaSalida) {
        super(fechaVinculacion, horaIngreso, horaSalida);
    }

    public Mesero(String nombre, String cedula, String telefono, String correo) {
        super(nombre, cedula, telefono, correo);
    }

    public Mesero(double Salario) {
        this.Salario = Salario;
    }

    public void tomarPedido(String s1){
        System.out.println("Se ha tomado pedido de " +s1);
    };

    public void cancelarPedido(String s1){
        System.out.println("Se ha cancelado pedido de " +s1);
    };

    public void modificarPedido(String s1){
        System.out.println("Se ha modificado pedido de " +s1);
    };

    public void entregarPedido(String s1){
        System.out.println("Se ha entregado pedido de " +s1);
    };

    public double getSalario() {
        return Salario;
    }

    public void setSalario(double Salario) {
        this.Salario = Salario;
    }

}
