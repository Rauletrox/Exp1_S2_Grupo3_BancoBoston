/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exp1_s2_grupo03_bankboston;

import java.text.NumberFormat;

/**
 * ---------Cree una subclase llamada Cuenta Corriente------------
 * 
 */
public class CuentaCorriente extends Cuenta {
    private static final int LIMITE_SOBREGIRO = 100000;
    
    @Override
    public String obtenerTipoCuenta() {
        return "Cuenta Corriente";
    }
    
    @Override
    public boolean girar(int monto) {
        if (monto <= 0) {
            System.err.println("\nMonto invalido. Debe ser mayor a 0.");
            return false;
        } 
        
         /*Aquí voy a colocar un formateo de numero para que nos muestre el $ y el punto*/
        NumberFormat pesos=NumberFormat.getCurrencyInstance();
        
        int saldoDisponible = saldo + LIMITE_SOBREGIRO;
        if (monto > saldoDisponible) {
            System.err.println("\nLímite de sobregiro excedido. Saldo disponible: " + pesos.format(saldoDisponible));
            return false;
        }

        saldo -= monto;
        System.out.println("\nGiro exitoso. Nuevo saldo: " + pesos.format(saldo));
        
        if(saldo < 0) {
            System.out.println("(Usted tiene un sobregiro de " + pesos.format((-saldo)));
        }
        
        return true;
    }
}