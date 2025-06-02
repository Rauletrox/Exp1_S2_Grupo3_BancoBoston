/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exp1_s2_grupo03_bankboston;

import java.text.NumberFormat;

/**
 *
 * @author usuario
 */
public class CuentaCredito extends Cuenta{
    
    private static final int LIMITE_CREDITO = 500000; // Limite de credito permitido
    
    // Formateador de moneda 
    NumberFormat pesos = NumberFormat.getCurrencyInstance();
    
    @Override
    public String obtenerTipoCuenta(){
        return "Cuenta de Credito";        
    }
    
    @Override
    public boolean girar(int monto) {
        if (monto <= 0) {
            System.err.println("\nMonto invalido. Debe ser mayor a 0.");
            return false;
        }

        int saldoDisponible = saldo + LIMITE_CREDITO;

        if (monto > saldoDisponible) {
            System.err.println("\nCredito insuficiente. Saldo disponible: " + pesos.format(saldoDisponible));
            return false;
        }

        saldo -= monto;
        System.out.println("\nGiro exitoso. Nuevo saldo: " + pesos.format(saldo));

        if (saldo < 0) {
            System.out.println("Ha utilizado " + pesos.format(-saldo) + " de su linea de credito.");
        }

        return true;
    }
    
}
