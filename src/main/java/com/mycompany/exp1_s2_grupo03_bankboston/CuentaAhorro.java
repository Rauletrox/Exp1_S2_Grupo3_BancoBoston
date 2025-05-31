/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exp1_s2_grupo03_bankboston;

import java.text.NumberFormat;

/*------------Cree la subclase Cuenta Ahorro---------------------*/

public class CuentaAhorro extends Cuenta {
    private static final double INTERES_ANUAL = 0.05; // 5% de interés anual
    
    @Override
    public String obtenerTipoCuenta() {
        return "Cuenta de Ahorro";
    }
    
     /*Aquí voy a colocar un formateo de numero para que nos muestre el $ y el punto*/
    NumberFormat pesos=NumberFormat.getCurrencyInstance();
    
    @Override
    public boolean girar(int monto) {
        if (monto <= 0) {
            System.err.println("\nMonto invalido. Debe ser mayor a 0.");
            return false;
        } 
        
        if (monto > saldo) {
            System.err.println("\nSaldo insuficiente. Saldo actual: " + pesos.format(saldo));
            return false;
        }

        saldo -= monto;
        System.out.println("\nGiro exitoso. Nuevo saldo: " +pesos.format(saldo));
        return true;
    }
    
   
    public void aplicarInteresMensual() {
        double interes = saldo * (INTERES_ANUAL / 12);
        saldo += (int) interes;
        System.out.println("Interés mensual aplicado: " + pesos.format((int) interes));
    }
}