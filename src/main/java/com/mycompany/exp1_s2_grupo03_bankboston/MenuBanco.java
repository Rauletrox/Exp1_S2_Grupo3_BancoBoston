/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exp1_s2_grupo03_bankboston;
import java.util.Scanner;

// Clase que representa los menu del sistema bancario
public class MenuBanco {
    
    // Metodo que muestra el menu principal con las opciones generales del sistema
    public static int mostrarMenuPrincipal(Scanner sc) {
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("                 🏦Bienvenido al " + Constantes.NOMBRE_BANCO +"🏦                  ");
        System.out.println("-------------------------------------------------------------");
        System.out.println("\n¿Que deseas que hagamos por ti?");
        System.out.println("\n1. 👉 Hazte cliente"); /*Lo escribí así ya que así sale en muchos bancos actuales*/
        System.out.println("2. 🧾 Revisar mi cuenta");/*También lo cambié por lo mismo que lo anterior*/
        System.out.println("3. 👋 Salir");
        return Validador.validarRangoEntero(sc, "\nSeleccione una opcion (1,2 o 3): ", 1, 3);
    }
    
    // Metodo que muesta el menu de operaciones interno disponible para un cliente ya validado con rut y contraseña
    public static int mostrarMenuCliente(Scanner sc, Cliente cliente) {
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("               💻 Menu de operaciones del Cliente 💻");
        System.out.println("                 " + cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno());
        System.out.println("-------------------------------------------------------------");  
        System.out.println("\n1. 📝 Ver datos del cliente");
        System.out.println("2. 💲 Realizar deposito");
        System.out.println("3. 💸 Realizar giro");
        System.out.println("4. 🪙 Consultar saldo");
        System.out.println("5. 🔙 Volver al menu principal");
        System.out.println("6. 👋 Salir");
        return Validador.validarRangoEntero(sc, "\nSeleccione una opcion: ", 1, 6);
    }
}
