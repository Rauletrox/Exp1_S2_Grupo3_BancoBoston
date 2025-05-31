/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exp1_s2_grupo03_bankboston;

public abstract class Persona implements Mostrable {
    protected String rut;
    protected String nombre;
    
    public Persona(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }
    
    // Constructor sobrecargado
    public Persona(String rut, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this(rut, nombre);
        // En clases hijas se usarán los apellidos
    }
    
    // Método abstracto
    public abstract String obtenerIdentificacionCompleta();
    
    // Getters
    public String getRut() { return rut; }
    public String getNombre() { return nombre; }
}