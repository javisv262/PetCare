package com.petcare.main;

import com.petcare.entity.Veterinario;
import com.petcare.service.VeterinarioService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class menuVeterinarios {
    static VeterinarioService vs;
    static Scanner teclado = new Scanner(System.in);
    public static void visualizar(){
        vs = new VeterinarioService();
        int opcion = 0;

        do {
            try {
                System.out.println("==========================================");
                System.out.println("  Módulo de Veterinarios");
                System.out.println("==========================================");
                System.out.println("1. Registrar veterinario");
                System.out.println("2. Buscar por colegiado");
                System.out.println("3. Listar veterinarios");
                System.out.println("4. Modificar especialidad");
                System.out.println("5. Eliminar veterinario");
                System.out.println("6. Salir");
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        registrar_veterinario();
                        break;
                    case 2:
                        buscar_colegiado();
                        break;
                    case 3:
                        listar_veterinarios();
                        break;
                    case 4:
                        modificar_especialidad();
                        break;
                    case 5:
                        eliminar_veterinario();
                        break;
                    case 6:
                        System.out.println("Regresando a la seleccion de modulo....");
                        break;
                    default:
                        System.out.println("Ingrese una opcion valida");
                }
            }catch (InputMismatchException e){
                System.out.println("Error, solo se aceptan valores numericos del 1 al 6");
            }
        }while(opcion!=6);
    }
    private static void modificar_especialidad(){
        Veterinario v = new Veterinario();
        try {
            System.out.println("Introduce el ID del veterinario a modificar");
            v.setId(teclado.nextInt());
            System.out.println("Introduce la nueva especialidad");
            v.setEspecialidad(teclado.next());
            vs.modificar_especialidad(v);

        }catch(InputMismatchException e){
            System.out.println("Error, se ha introducido un valor invalido");
        }
    }
    private static void listar_veterinarios(){
        vs.listar_veterinarios();
    }
    private static void eliminar_veterinario(){
        Veterinario v = new Veterinario();
        try {
            System.out.println("Introduce el ID del veterinario a eliminar");
            v.setId(teclado.nextInt());
            vs.eliminar_veterinario(v);

        }catch(InputMismatchException e){
            System.out.println("Error, se ha introducido un valor invalido");
        }
    }
    private static void buscar_colegiado(){
        Veterinario v = new Veterinario();
        System.out.println("Introduce el numero de colegiado del veterinario");
        v.setNumColegiado(teclado.next());
        vs.buscar_colegiado(v);

    }
    private static void registrar_veterinario(){
        try {
            Veterinario v = new Veterinario();
            System.out.println("Introduce el numero de colegiado (7 caracteres)");
            v.setNumColegiado(teclado.next());
            System.out.println("Introduce el nombre del veterinario");
            v.setNombre(teclado.next());
            System.out.println("Introduce la especialidad del veterinario");
            v.setEspecialidad(teclado.next());

            if(vs.altaVeterinario(v)){
                System.out.println("Veterinario registrado correctamente");
            }else {
                System.out.println("Ha habido un problema al registrar el veterinario");
            }
        }catch (InputMismatchException e){
            System.out.println("Error, se ha introducido un valor invalido");
        }

    }
}
