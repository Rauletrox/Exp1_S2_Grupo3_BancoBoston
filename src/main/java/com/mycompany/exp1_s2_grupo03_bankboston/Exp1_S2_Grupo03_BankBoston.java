/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

// Grupo 3 - Raul Zuñiga / Francisco Henriquez

//Experiencia SEMANA 02 - PROGRAMACIÓN ORIENTADA A OBJETOS

package com.mycompany.exp1_s2_grupo03_bankboston;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exp1_S2_Grupo03_BankBoston {

    public static void main(String[] args) {
 // Lista que almacena los clientes del banco
        List<Cliente> clientes = new ArrayList<>();
        // Scanner para entrada de datos
        Scanner sc = new Scanner(System.in);
        // Objeto que gestiona las operaciones del banco
        OperacionesBanco operacion = new OperacionesBanco(clientes, sc);
        
        boolean continuarSistema = true;
        
        //Ciclo principal del sistema
        while (continuarSistema){
            int opcionMenu = MenuBanco.mostrarMenuPrincipal(sc);

            switch (opcionMenu){
                case 1: 
                    // Registro de cleinte
                    operacion.registrarCliente();
                    continuarSistema = operacion.deseaOtraOperacion(sc);   
                    break;
                case 2:
                    // Acceso a la cuenta del cliente
                    operacion.revisarCuentaCliente();  
                    break;
                case 3:
                    // Salida del sistema
                    System.out.println("Gracias por usar " + Constantes.nombreBanco);
                    continuarSistema = false;
            }
        }
        sc.close();
    }
}
