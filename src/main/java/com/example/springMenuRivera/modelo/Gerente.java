package com.example.springMenuRivera.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "gerente")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("Gerente")
public class Gerente extends Persona{

    public Gerente() {
    }

    public Gerente(String nombre) {
    }


    public Gerente(String nombre, String cedula, String telefono, String correo) {
        super(nombre, cedula, telefono, correo);
    }

    public void agregarEmpleado(){
        System.out.println("AGREGAR EMPLEADO : ");
    };
    public void borrarEmpleado(){
        System.out.println("BORRAR EMPLEADO");
    };
    public void modificarEmpleado(){
        System.out.println("MODIFICAR EMPLEADO");
    };
    public void visualizarVentas(){
        System.out.println("VISUALIZAR VENTAS");
    };
    public void visualizarPedidos(){
        System.out.println("VISUALIZAR PEDIDOS");
    };
    public void generarPagoEmpleado(String pago){
        System.out.println("GENERAR PAGO A EMPLEADO : "+pago);
    };
    public void agregarItemDespensa(String item) { System.out.println("Item agregado: " + item); }
    public void eliminarItemDespensa(String item) { System.out.println("Item eliminado: " + item); }
}
