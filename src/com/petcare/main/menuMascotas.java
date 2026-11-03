package com.petcare.main;

import com.db4o.ObjectSet;
import com.petcare.dao.MascotaDAO;
import com.petcare.entity.Mascota;
import com.petcare.service.MascotaService;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class menuMascotas {
    static MascotaService ms;
    static Scanner teclado = new Scanner(System.in);
    public static void visualizar() {
        ms = new MascotaService();
        int opcion = 0;

        do {
            try {
                System.out.println("==========================================");
                System.out.println("  Módulo de Mascotas");
                System.out.println("==========================================");
                System.out.println("1. Registrar mascota");
                System.out.println("2. Buscar por microchip");
                System.out.println("3. Listar mascotas");
                System.out.println("4. Modificar datos");
                System.out.println("5. Eliminar mascota");
                System.out.println("6. Salir");
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        registrar_mascota();
                        break;
                    case 2:
                        buscar_chip();
                        break;
                    case 3:
                        listar_mascotas();
                        break;
                    case 4:
                        modificar_mascota();
                        break;
                    case 5:
                        eliminar_mascota();
                        break;
                    case 6:
                        System.out.println("Regresando a la seleccion de modulo....");
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                }

            }catch(InputMismatchException ex) {
                System.out.println("Introduce un valor numerico del 1 al 6");
            }
        }while(opcion!=6);
    }
    private static void eliminar_mascota() {
        Mascota m = new Mascota();
        try {
            System.out.println("Introduce el id de la mascota a eliminar");
            m.setId(teclado.nextInt());
            ms.eliminar_mascota(m);
        } catch (InputMismatchException e) {
            System.out.println("Error, el valor introducido es invalido");
        }
    }
    private static void modificar_mascota() {
        Mascota m = new Mascota();
        try {
            System.out.println("Introduce el ID de la mascota cuyos datos se van a modificar");
            m.setId(teclado.nextInt());
            System.out.println("Rellena la nueva informacion que sustituira la anterior:");
            System.out.println("Introduce el nombre de la mascota ");
            m.setNombre(teclado.next());
            System.out.println("Introduce la especie de la mascota ");
            m.setEspecie(teclado.next().toLowerCase(Locale.ROOT));
            System.out.println("Introduce la edad de la mascota ");
            m.setEdad(teclado.nextInt());

            ms.modificarMascota(m);
        } catch (InputMismatchException ex) {
            System.out.println("Error, se ha introducido un valor invalido");
        }

    }
    private static void listar_mascotas() {
        ms.listar_mascotas();
    }
    private static void buscar_chip(){
        Mascota m = new Mascota();

        System.out.println("Introduce el chip");
        m.setChip(teclado.next());
        ms.buscarChip(m);

    }
    private static void registrar_mascota() {
        String chip = "";
        String nombre = "";
        String especie = "";
        int edad = 0;
        try {
            System.out.println("Introduce el chip(7 caracteres)");
            chip = teclado.next();
            System.out.println("Introduce el nombre");
            nombre = teclado.next();
            System.out.println("Introduce la especie");
            especie = teclado.next().toLowerCase(Locale.ROOT);
            System.out.println("Introduce la edad");
            edad = teclado.nextInt();
        }catch(InputMismatchException ex) {
            System.out.println("Error, asegurate de introducir valores validos");
            return;
        }
        Mascota m = new Mascota(chip, nombre, especie, edad);

       if(ms.altaMascota(m)){
           System.out.println("Mascota registrado exitosamente");
       }else {
           System.out.println("Error registrando la mascota");
       }
    }
}
