package com.petcare.main;
import java.util.*;
public class App {

   static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int modulo = 0;
        do {
            try {
                System.out.println("==========================================");
                System.out.println("  SISTEMA VETERINARIO PETCARE (BDOO)");
                System.out.println("==========================================");
                System.out.println("1. Módulo de Mascotas");
                System.out.println("2. Módulo de Veterinarios");
                System.out.println("3. Salir");
                System.out.println("==========================================");
                modulo = teclado.nextInt();
                switch (modulo) {
                    case 1:
                        menuMascotas.visualizar();
                        break;
                    case 2:
                        menuVeterinarios.visualizar();
                        break;
                    case 3:
                        System.out.println("Finalizando programa...");
                        System.exit(0);

                    default:
                        System.out.println("Ingrese una opcion valida");

                }

            }catch(InputMismatchException ex) {
                System.out.println("Introduce un valor numerico del 1 al 3");
            }

        }while (modulo != 3);

    }
}
