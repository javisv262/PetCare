package com.petcare.dao;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.petcare.entity.Mascota;
import com.petcare.util.ConexionDb4o;

import java.util.Objects;

public class MascotaDAO {
    ConexionDb4o conexionDb4o;
    public boolean Altamascota (Mascota m) {
        conexionDb4o = new ConexionDb4o();
        ObjectContainer bd =conexionDb4o.abrirConexion();

        if(comprobarChip(bd,m)){
            System.out.println("El chip esta repetido");
            bd.close();
            return false;
        }
        int id = calcularID(bd);
        m.setId(id);

        bd.store(m);
        bd.commit();
        bd.close();
        return true;
    }
    public Mascota buscarChip(Mascota m){
        conexionDb4o = new ConexionDb4o();
        ObjectContainer bd =conexionDb4o.abrirConexion();
        Mascota mascota = buscarAtributo(m,bd);
        bd.close();
        return mascota;

    }
    private int calcularID(ObjectContainer bd){
        ObjectSet<Mascota> todasMascotas = bd.queryByExample(new Mascota());
        int maxId = 0;
        for (Mascota mascota : todasMascotas) {
            if(mascota.getId()>maxId){
                maxId = mascota.getId();
            }

        }
        return maxId+1;
    }
    public void listar_mascotas(){
        conexionDb4o = new ConexionDb4o();
        ObjectContainer bd =conexionDb4o.abrirConexion();
        ObjectSet<Mascota> todasMascotas = bd.queryByExample(new Mascota());
        for (Mascota m : todasMascotas) {
            System.out.println(m);
        }
        bd.close();
    }
    private Mascota buscarAtributo(Mascota mascota, ObjectContainer bd){
        ObjectSet<Mascota> todasMascotas = bd.queryByExample(mascota);
        Mascota m = null;
        if(todasMascotas.hasNext()){
            m = todasMascotas.next();
        }
        return m;
    }
    public boolean eliminar_mascota(Mascota mascota) {
        conexionDb4o = new ConexionDb4o();
        ObjectContainer bd =conexionDb4o.abrirConexion();
        Mascota m = buscarAtributo(mascota,bd);

        if(m!=null){
            bd.delete(m);
            bd.commit();
            bd.close();
            return true;
        }else {
            bd.close();
            return false;
        }

    }

    private boolean comprobarChip (ObjectContainer bd, Mascota m) {
        ObjectSet<Mascota> todasMascotas = bd.queryByExample(new Mascota());
        for (Mascota mascota : todasMascotas) {
            if (Objects.equals(mascota.getChip(), m.getChip())) {
                return true;
            }
        }
        return false;
    }
    public boolean modificarMascota(Mascota mascota) {
        conexionDb4o = new ConexionDb4o();
        ObjectContainer bd =conexionDb4o.abrirConexion();
        Mascota m = new Mascota();
        m.setId(mascota.getId());
        m = buscarAtributo(m,bd);
        if(m!=null){
            m.setNombre(mascota.getNombre());
            m.setEspecie(mascota.getEspecie());
            m.setEdad(mascota.getEdad());

            bd.store(m);
            bd.commit();
            bd.close();
            return true;
        }else {
            bd.close();
            return false;
        }
  }
}
