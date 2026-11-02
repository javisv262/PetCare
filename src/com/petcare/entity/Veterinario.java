package com.petcare.entity;

public class Veterinario {
    private int id;
    private String numColegiado;
    private String nombre;
    private String especialidad;

    public Veterinario() {}
    public Veterinario(int id,String numColegiado, String nombre, String especialidad) {
        this.id = id;
        this.numColegiado = numColegiado;
        this.nombre = nombre;
        this.especialidad = especialidad;

    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "ID: " + id +"numColegiado: "+numColegiado+ " Nombre: " + nombre + " Especialidad: " + especialidad;
    }
}
