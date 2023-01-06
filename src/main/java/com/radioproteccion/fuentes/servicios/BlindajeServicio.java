/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.servicios;

import com.radioproteccion.fuentes.entidades.Blindaje;
import com.radioproteccion.fuentes.repositorios.BlindajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jaguirre89
 */
@Service
public class BlindajeServicio {
    
    @Autowired
    private BlindajeRepositorio blindajeRepositorio;
    
    public Blindaje crear (Blindaje blindaje){
        return blindajeRepositorio.save(blindaje);
    }
    
    public Blindaje buscarPorId(String id){
        return blindajeRepositorio.findById(id).get();
    }
    
    public Blindaje modificar(Blindaje blindaje){
        Blindaje blindajeDB = blindajeRepositorio.getById(blindaje.getId());
        
        blindajeDB.setModelo(blindaje.getModelo());
        blindajeDB.setAltura(blindaje.getAltura());
        blindajeDB.setRadio(blindaje.getRadio());
        blindajeDB.setPeso(blindaje.getPeso());
        
        return blindajeRepositorio.save(blindajeDB);
    }
    
    public void eliminar (Blindaje blindaje){
        blindajeRepositorio.delete(blindaje);
    }
    
}
