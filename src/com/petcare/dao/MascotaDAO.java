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
            return false;
        }
        int id = calcularID(bd);
        m.setId(id);

        bd.store(m);
        bd.commit();
        bd.close();
        return true;
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

    private boolean comprobarChip (ObjectContainer bd, Mascota m) {
        ObjectSet<Mascota> todasMascotas = bd.queryByExample(new Mascota());
        for (Mascota mascota : todasMascotas) {
            if (Objects.equals(mascota.getChip(), m.getChip())) {
                return true;
            }
        }
        return false;
    }
}
