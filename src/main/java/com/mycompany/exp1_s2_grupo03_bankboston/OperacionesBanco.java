/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exp1_s2_grupo03_bankboston;

import java.util.List;
import java.util.Scanner;

// Clase que gestiona todas las operaciones del banco para sus clientes
public class OperacionesBanco {
    
    private List<Cliente> clientes; // Lista de todos los clientes
    private Scanner sc; 
    
    // Constructor - Revice la lista de clientes y el scanner
    public OperacionesBanco (List <Cliente> clientes, Scanner sc){
        this.clientes = clientes;
        this.sc = sc;    
    }    
    
    // Metodo que registra un nuevo cliente solicitando sus datos y contraseña
    public void registrarCliente() {    
        
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("            📝Sistema para el Registro del Cliente📝");
        System.out.println("-------------------------------------------------------------");
        
        String rut = Validador.validarRut(sc, "\nIngrese Rut con puntos y guion (Ej: 12.345.678-9) o 'c' para cancelar: ");
        if (rut == null) return;
        
        if(Validador.esRutDuplicado(rut, clientes, "\nError: Cliente ya registrado con ese RUT. Cada cliente puede tener una sola cuenta contratada.")){
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido Paterno: ");
        String apPaterno = sc.nextLine();
        System.out.print("Apellido Materno: ");
        String apMaterno = sc.nextLine();
        System.out.print("Domicilio: ");
        String domicilio = sc.nextLine();
        System.out.print("Comuna: ");
        String comuna = sc.nextLine();
        System.out.print("Telefono (Ej: (+56)977664453: ");/*Solo para que fuera entendible*/
        String telefono = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        
        /*Seleccion del tipo de cuenta*/
        
        System.out.println("\nSeleccione tipo de cuenta:");
        System.out.println("1. Cuenta Corriente 💳");
        System.out.println("2. Cuenta de Ahorro 💵");
        System.out.println("3. Cuenta de Credito 💵");
        
        int tipoCuenta = Validador.validarRangoEntero(sc, "\nIndique opcion (1 - 3): ", 1, 3);
        
        Cliente nuevo = new Cliente(rut, nombre, apPaterno, apMaterno, 
                                   domicilio, comuna, telefono, contrasena);

        // Crear cuenta según tipo seleccionado
        switch (tipoCuenta) {
            case 1:
                nuevo.setCuenta(new CuentaCorriente());
                break;
            case 2:
                nuevo.setCuenta(new CuentaAhorro());
                break;
            case 3:
                nuevo.setCuenta(new CuentaCredito());
                break;
            default:
                System.err.println("Opción no válida.");
        }
        
        clientes.add(nuevo);
        
        System.out.println("\n🧾 Numero de " + nuevo.getCuenta().obtenerTipoCuenta() + "asignada: " + nuevo.getCuenta().getNumeroCuenta());
        System.out.println("🎊Cliente registrado con exito.🎊");
    }  
        
    
    // Metodo que busca en la lista de clientes por su RUT
    public Cliente buscarClientePorRut(String rut) {
        
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getRut().equalsIgnoreCase(rut)) {
                return clientes.get(i);
            }
        }
        return null;
    }
    
    // Metodo que permite al cliente acceder a su cuenta y realizar operaciones mediante un menu interno
    public void revisarCuentaCliente (){
              
        Cliente cliente = null;
        while (cliente == null) {
            System.out.print("\nIngrese RUT del cliente registrado (Ej: 12.345.678-9) o 'c' para cancelar: ");
            String rut = sc.nextLine().trim();

            if (rut.equalsIgnoreCase("c")) {
                System.out.println("Operacion cancelada.");
                return;
            }

            cliente = buscarClientePorRut(rut);

            if (cliente == null) {
                System.err.println("🚨Cliente no encontrado🚨 Intente nuevamente o escriba 'c' para cancelar.");
            }
        }
        
        boolean contrasenaValida = false;
        while (!contrasenaValida){
            System.out.print("\nIngrese contraseña del cliente registrado o 'c' para cancelar: ");
            String contrasena = sc.nextLine();

            if (contrasena.equalsIgnoreCase("c")) {
                System.out.println("🚨Operacion cancelada🚨");
                return;
            }
            
            contrasenaValida = cliente.getContrasena().equals(contrasena);

            if (contrasenaValida == false) {
                System.err.println("🚨Contraseña incorrecta🚨 Intente nuevamente o escriba 'c' para cancelar.");
            }        
        }
            
        boolean continuar = true;
        while (continuar){
            int opcionMenu = MenuBanco.mostrarMenuCliente(sc, cliente);

            switch (opcionMenu){
                case 1: 
                    cliente.mostrarInformacion();
                    continuar = deseaOtraOperacion(sc);   
                    if (!continuar){
                        System.exit(0);                    
                    }
                    break;
                case 2:
                    procesarOperacion(cliente, "depositos");
                    continuar = deseaOtraOperacion(sc);   
                    if (!continuar){
                        System.exit(0);                    
                    }
                    break;
                case 3:
                    procesarOperacion(cliente, "giros");
                    continuar = deseaOtraOperacion(sc);   
                    if (!continuar){
                        System.exit(0);                    
                    }
                    break;
                case 4:
                    cliente.getCuenta().consultarSaldo();
                    continuar = deseaOtraOperacion(sc);   
                    if (!continuar){
                        System.exit(0);                    
                    }
                    break;
                case 5:
                    return;
                case 6:
                    System.out.println("Gracias por usar " + Constantes.NOMBRE_BANCO);
                    System.exit(0);
            }
        }              
    }
    
    // Metodo auxiliar que permite pregunta si el usuario desea realizar otra operacion. 
    public boolean deseaOtraOperacion(Scanner sc) {
        int opcion = Validador.validarRangoEntero(sc, "\n¿Desea realizar otra operacion? (1. Si / 2. No): ", 1, 2);
        if (opcion == 2) {
            System.out.println("Gracias por usar " + Constantes.NOMBRE_BANCO);
            return false;
        }
        return opcion == 1;
    }    
    
    // Metodo que procesa el deposito o giro segun solicitud del usuario
    public void procesarOperacion(Cliente cliente, String operacion) {
        
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("               ‍💻Sistema para realizar " + operacion + " ‍💻");
        System.out.println("-------------------------------------------------------------");  
        
        boolean operacionExitosa = false;
        
        while (!operacionExitosa){
            switch (operacion) {
                case "depositos":
                    int montoDeposito = -1;
                    montoDeposito = Validador.validarMonto(sc, "\nIngrese monto a depositar o 'c' para cancelar: ");
                    if (montoDeposito == -1){
                        return;
                    }
                    if (montoDeposito == -2){
                        continue;
                    }
                    operacionExitosa = cliente.getCuenta().depositar(montoDeposito);
                    break;

                case "giros":
                    int montoGirar = -1;
                    montoGirar = Validador.validarMonto(sc, "\nIngrese monto a girar o 'c' para cancelar: ");
                    if (montoGirar == -1){
                        return;
                    }
                    if (montoGirar == -2){
                        continue;
                    }
                    operacionExitosa = cliente.getCuenta().girar(montoGirar);
                    break;

                default:
                    System.out.println("\n🚨Operacion no valida🚨");
                    break;
            }
        }
    }      
}
