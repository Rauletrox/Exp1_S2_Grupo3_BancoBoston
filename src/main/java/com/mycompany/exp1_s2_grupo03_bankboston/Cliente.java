/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exp1_s2_grupo03_bankboston;

//Clase que representa a un cliente del banco

import java.text.NumberFormat;


/*------Aquí voy a modificar la clase Cliente para que extienda de persona
Nos permite heredar de persona------------------------------------------------*/
public class Cliente extends Persona implements Mostrable {
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String domicilio;
    private String comuna;
    private String telefono;
    private Cuenta cuenta;
    private String contrasena;
    
    public Cliente(String rut, String nombre, String apellidoPaterno, 
            String apellidoMaterno, String domicilio, String comuna, 
            String telefono, String contrasena) {
        super(rut, nombre, apellidoPaterno, apellidoMaterno);
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }
    
    /* NUEVO--> Implementación del método abstracto*/
    @Override
    public String obtenerIdentificacionCompleta() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }
    
    /*Aquí voy a colocar un formateo de numero para que nos muestre el $ y el punto*/
    NumberFormat pesos=NumberFormat.getCurrencyInstance();
    
    //NUEVO--> Implementación de la interfaz para mostrar los datos del cliente
    @Override
    public void mostrarInformacion() {
        System.out.println("\nNombre completo del cliente: " + obtenerIdentificacionCompleta());
        System.out.println("Rut del cliente: " + rut);
        System.out.println("Domicilio: " + domicilio + ", comuna: " + comuna);
        System.out.println("Telefono: " + telefono);
        System.out.println("Tipo de cuenta: " + cuenta.obtenerTipoCuenta());
        System.out.println("Numero de cuenta: " + cuenta.getNumeroCuenta());
        System.out.println("Saldo actual: " + pesos.format(cuenta.getSaldo()));
    }
    
    // Metodos getters y setter
    public String getRut(){
        return rut;
    }  
    
    public String getNombre(){
        return nombre;
    }
    
    public String getApellidoPaterno(){
        return apellidoPaterno;
    }
    
    public String getApellidoMaterno(){
        return apellidoMaterno;
    }
    
    public Cuenta getCuenta(){
        return cuenta;
    } 
    
    /*---------------------Método Setter--------------------*/
    
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    public String getContrasena(){
        return contrasena;
    }
}
