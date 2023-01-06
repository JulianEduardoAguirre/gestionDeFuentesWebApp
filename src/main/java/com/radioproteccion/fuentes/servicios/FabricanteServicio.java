/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.servicios;

import com.radioproteccion.fuentes.entidades.Fabricante;
import com.radioproteccion.fuentes.repositorios.FabricanteRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jaguirre89
 */
@Service
public class FabricanteServicio {
    
    @Autowired
    private FabricanteRepositorio fabricanteRepositorio;
    
    @Transactional(rollbackFor = Exception.class)
    public void crear(Fabricante fabricante){
         fabricanteRepositorio.save(fabricante);
    }
    
    public List<Fabricante> listarTodos(){
        return fabricanteRepositorio.findAll();
    }
    
    public Fabricante buscarPorId(String id){
        return fabricanteRepositorio.findById(id).get();
    }
    
    public List<Fabricante> buscarPorNombre (String nombre){
        List<Fabricante> fabricantes = fabricanteRepositorio.buscarPorNombre(nombre);
        
        return fabricantes;
    }
    
    public Fabricante modificar (Fabricante fabricante){
        Fabricante fabricanteDB = fabricanteRepositorio.findById(fabricante.getId()).get();
        
        fabricanteDB.setNombre(fabricante.getNombre());
        
        return fabricanteRepositorio.save(fabricanteDB);
    }
    
    public Boolean eliminar (Fabricante fabricante){
        fabricanteRepositorio.delete(fabricante);
        
        return true;
    }
}
