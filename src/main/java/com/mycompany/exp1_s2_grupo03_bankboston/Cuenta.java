/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exp1_s2_grupo03_bankboston;

import java.util.Random;
import java.text.NumberFormat;

public abstract class Cuenta {
    protected String numeroCuenta;
    protected int saldo;
    protected static final Random rand = new Random();
    
    public Cuenta() {
        this.numeroCuenta = generarNumeroCuenta();
        this.saldo = 0;
    }
    
    // Constructor sobrecargado
    public Cuenta(int saldoInicial) {
        this();
        this.saldo = saldoInicial;
    }
    
    private static String generarNumeroCuenta() {
        StringBuilder cuenta = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            cuenta.append(rand.nextInt(10));
        }
        return cuenta.toString();
    }
    
    // Método abstracto
    public abstract String obtenerTipoCuenta();
    
    /*Aquí voy a colocar un formateo de numero para que nos muestre el $ y el punto*/
    NumberFormat pesos=NumberFormat.getCurrencyInstance();
    
    // Métodos comunes
    public boolean depositar(int monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("\nDeposito exitoso. Nuevo saldo: " + pesos.format(saldo));
            return true;
        } else {
            System.err.println("\nMonto invalido. Debe ser mayor a $ 0.");
            return false;
        }
    }
    
    public abstract boolean girar(int monto);
    
    public void consultarSaldo() {
        System.out.println("\nSaldo actual: " + pesos.format(saldo));
    }
    
    // Getters
    public String getNumeroCuenta() { return numeroCuenta; }
    public int getSaldo() { return saldo; }
}