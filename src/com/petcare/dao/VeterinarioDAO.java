package com.petcare.dao;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.petcare.entity.Mascota;
import com.petcare.entity.Veterinario;
import com.petcare.util.ConexionDb4o;

import java.util.Objects;

public class VeterinarioDAO {
    ConexionDb4o conexionDb4o;
    public boolean altaVeterinario (Veterinario v) {
        conexionDb4o = new ConexionDb4o();
        ObjectContainer bd =conexionDb4o.abrirConexion();

        if(comprobarNumcolegiado(v,bd)){
            System.out.println("El numero de colegiado esta repetido");
            bd.close();
            return false;
        }
        int id = calcularID(bd);
        v.setId(id);

        bd.store(v);
        bd.commit();
        bd.close();
        return true;
    }
    private int calcularID (ObjectContainer bd) {
        ObjectSet<Veterinario> todosVeterinarios = bd.queryByExample(new Veterinario());
        int maxId = 0;
        for (Veterinario veterinario : todosVeterinarios) {
            if(veterinario.getId()>maxId){
                maxId = veterinario.getId();
            }

        }
        return maxId+1;

    }
    public Veterinario buscar_numColegiado(Veterinario v){
        conexionDb4o = new ConexionDb4o();
        ObjectContainer bd =conexionDb4o.abrirConexion();
        Veterinario veterinario = buscarAtributo(v,bd);
        bd.close();
        return veterinario;
    }
    private Veterinario buscarAtributo(Veterinario veterinario, ObjectContainer bd){
        ObjectSet<Veterinario> todosVeterinarios = bd.queryByExample(veterinario);
        Veterinario v = null;
        if(todosVeterinarios.hasNext()){
            v = todosVeterinarios.next();
        }
        return v;
    }
    public boolean modificar_especialidad(Veterinario veterinario) {
        conexionDb4o = new ConexionDb4o();
        ObjectContainer bd =conexionDb4o.abrirConexion();
        Veterinario v = new Veterinario();
        v.setId(veterinario.getId());
        v = buscarAtributo(v,bd);
        if(v!=null){
            v.setEspecialidad(veterinario.getEspecialidad());

            bd.store(v);
            bd.commit();
            bd.close();
            return true;
        }else {
            bd.close();
            return false;
        }
    }
    public void listar_veterinarios(){
        conexionDb4o = new ConexionDb4o();
        ObjectContainer bd =conexionDb4o.abrirConexion();
        ObjectSet<Veterinario> todosVeterinarios = bd.queryByExample(new Veterinario());
        for (Veterinario v : todosVeterinarios) {
            System.out.println(v);
        }
        bd.close();
    }
    public boolean eliminar_veterinario(Veterinario veterinario) {
        conexionDb4o = new ConexionDb4o();
        ObjectContainer bd =conexionDb4o.abrirConexion();
        Veterinario v = buscarAtributo(veterinario,bd);

        if(v!=null){
            bd.delete(v);
            bd.commit();
            bd.close();
            return true;
        }else {
            bd.close();
            return false;
        }

    }
    private boolean comprobarNumcolegiado(Veterinario v, ObjectContainer bd) {
        ObjectSet<Veterinario> todosVeterinarios = bd.queryByExample(new Veterinario());
        for (Veterinario veterinario : todosVeterinarios) {
            if (Objects.equals(veterinario.getNumColegiado(), v.getNumColegiado())) {
                return true;
            }
        }
        return false;
    }
}
