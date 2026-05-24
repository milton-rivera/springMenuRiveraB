package com.example.springMenuRivera.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "empleado")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("Empleado")
public class Empleado extends Persona {
    @Transient
    private LocalDate fechaVinculacion;
    @Transient
    private LocalTime horaIngreso;
    @Transient
    private LocalTime horaSalida;

    @Column(name = "salario")
    private double salario;
    public Empleado() {
    }

    public Empleado(String nombre) {
        super(nombre);
    }

    public Empleado(LocalDate fechaVinculacion, LocalTime horaIngreso, LocalTime horaSalida) {
        this.fechaVinculacion = fechaVinculacion;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
    }

    public Empleado(String nombre, String cedula, String telefono, String correo) {
        super(nombre, cedula, telefono, correo);
    }

    public LocalDate getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(LocalDate fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }







    public void registrarEntrada(LocalTime horaIngreso){
        System.out.println("ENTRADA REGISTRADA, HORA : "+ horaIngreso);
    }
    public void registrarSalida(LocalTime horaSalida){
        System.out.println("SALIDA REGISTRADA, HORA : "+ horaSalida);
    }
}
