package com.petcare.service;

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

}
