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
    
    @Transactional(readOnly = true)
    public List<Fabricante> listarTodos(){
        return fabricanteRepositorio.findAll();
    }
    
    @Transactional(readOnly = true)
    public Fabricante buscarPorId(String id){
        return fabricanteRepositorio.findById(id).get();
    }
    
    @Transactional(readOnly = true)
    public List<Fabricante> buscarPorNombre (String nombre){
        
        return fabricanteRepositorio.buscarPorNombre(nombre);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public Fabricante modificar (String id, Fabricante fabricante){
        Fabricante fabricanteDB = fabricanteRepositorio.findById(id).get();
        
        fabricanteDB.setNombre(fabricante.getNombre());
        fabricanteDB.setTelefono(fabricante.getTelefono());
        fabricanteDB.setContacto(fabricante.getContacto());
        
        return fabricanteRepositorio.save(fabricanteDB);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void eliminar (String fabricanteId){
        fabricanteRepositorio.deleteById(fabricanteId);
        
    }
}
