package com.petcare.service;

import com.petcare.dao.MascotaDAO;
import com.petcare.dao.VeterinarioDAO;
import com.petcare.entity.Mascota;
import com.petcare.entity.Veterinario;

public class VeterinarioService {
    VeterinarioDAO veterinarioDAO;
    public boolean altaVeterinario(Veterinario v) {
        veterinarioDAO = new VeterinarioDAO();
        if (v.getNumColegiado().length() != 7){
            return false;
        }
        if(v.getNombre().length() < 2 || v.getNombre().length() > 50){
            return false;
        }

        return veterinarioDAO.altaVeterinario(v);
    }
    public void modificar_especialidad(Veterinario v) {
        veterinarioDAO = new VeterinarioDAO();
        boolean estaModificado = veterinarioDAO.modificar_especialidad(v);
        if (estaModificado) {
            System.out.println("Especialidad modificada con exito");
        } else  {
            System.out.println("No hay un veterinario asociado al ID introducido");
        }
    }
    public void listar_veterinarios(){
        veterinarioDAO = new VeterinarioDAO();
        veterinarioDAO.listar_veterinarios();
    }
    public void  eliminar_veterinario(Veterinario v) {
       veterinarioDAO = new VeterinarioDAO();
        boolean estaEliminado = veterinarioDAO.eliminar_veterinario(v);
        if (estaEliminado) {
            System.out.println("Veterinario Eliminado con exito");
        }else  {
            System.out.println("El veterinario no existe");
        }

    }
    public void buscar_colegiado(Veterinario v) {
        veterinarioDAO = new VeterinarioDAO();

        if (v.getNumColegiado().length() != 7){
            System.out.println("El numero de colegiado introducido no esta en un formato valido");
        }else {
            Veterinario veterinario = veterinarioDAO.buscar_numColegiado(v);
            if (veterinario != null){
                System.out.println(veterinario);
            }else {
                System.out.println("No hay ningun veterinario asociado al numero de colegiado introducido");
            }
        }
    }
}
