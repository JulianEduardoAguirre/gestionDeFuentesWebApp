/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.servicios;

import com.radioproteccion.fuentes.entidades.Blindaje;
import com.radioproteccion.fuentes.repositorios.BlindajeRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jaguirre89
 */
@Service
public class BlindajeServicio {
    
    @Autowired
    private BlindajeRepositorio blindajeRepositorio;
    
    @Transactional(rollbackFor = Exception.class)    
    public Blindaje crear (Blindaje blindaje){
        return blindajeRepositorio.save(blindaje);
    }
    
    @Transactional(readOnly = true)
    public Blindaje buscarPorId(String id){
        return blindajeRepositorio.findById(id).get();
    }
    
    @Transactional(readOnly = true)
    public List<Blindaje> listarTodos(){
        return blindajeRepositorio.findAll();
    }
    
    @Transactional(rollbackFor = Exception.class)
    public Blindaje modificar(String id, Blindaje blindaje){
        Blindaje blindajeDB = blindajeRepositorio.getById(id);
        
        blindajeDB.setModelo(blindaje.getModelo());
        blindajeDB.setAltura(blindaje.getAltura());
        blindajeDB.setRadio(blindaje.getRadio());
        blindajeDB.setPeso(blindaje.getPeso());
        
        return blindajeRepositorio.save(blindajeDB);
    }
    
    @Transactional(readOnly = true)
    public void eliminar (String blindajeId){
        blindajeRepositorio.deleteById(blindajeId);
    }
    
}
