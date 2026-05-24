package com.example.springMenuRivera.modelo;

public class Cliente extends Persona {

    public Cliente(String nombre, String cedula, String telefono, String correo) {
        super(nombre, cedula, telefono, correo);
    }

    public Cliente() {
    }

    public void registrarse(String s1){
        System.out.println("Se ha registrado"+s1);
    };
    public void reservarMesa(){
        System.out.println("Se ha reservado mesa");
    };
    public void cancelarReservacion(){
        System.out.println("Se ha cancelado la reserva");
    };

}
