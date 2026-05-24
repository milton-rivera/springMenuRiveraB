package com.example.springMenuRivera.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "chef")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("Chef")
public class Chef extends Empleado {

    public Chef() {
    }

    public Chef(String nombre) {
        super(nombre);
    }

    public Chef(LocalDate fechaVinculacion, LocalTime horaIngreso, LocalTime horaSalida) {
        super(fechaVinculacion, horaIngreso, horaSalida);
    }

    public Chef(String nombre, String cedula, String telefono, String correo) {
        super(nombre, cedula, telefono, correo);
    }

}