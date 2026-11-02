package com.petcare.service;

import com.db4o.ObjectSet;
import com.petcare.dao.MascotaDAO;
import com.petcare.entity.Mascota;

public class MascotaService {
    MascotaDAO mascotaDAO;
    public boolean altaMascota(Mascota m) {
        mascotaDAO = new MascotaDAO();
        if (m.getChip().length() != 7){
            return false;
        }
        if(m.getNombre().length() < 2 || m.getNombre().length() > 50){
            return false;
        }

        return mascotaDAO.Altamascota(m);
    }

    public void buscarChip(Mascota m){
        mascotaDAO = new MascotaDAO();
        if (m.getChip().length() != 7){
            System.out.println("El chip introducido no esta en un formato valido");
        }else {
            Mascota mascota = mascotaDAO.buscarChip(m);
            if (mascota != null){
                System.out.println(mascota);
            }else {
                System.out.println("No hay ninguna mascota registrada con ese chip");
            }
        }
    }

}
