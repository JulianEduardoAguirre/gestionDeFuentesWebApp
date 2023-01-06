/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.servicios;

import com.radioproteccion.fuentes.entidades.Blindaje;
import com.radioproteccion.fuentes.entidades.Fabricante;
import com.radioproteccion.fuentes.entidades.Fuente;
import com.radioproteccion.fuentes.repositorios.FuenteRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jaguirre89
 */
@Service
public class FuenteServicio {
    
    @Autowired
    private FuenteRepositorio fuenteRepositorio;
    
    @Autowired
    private FabricanteServicio fabricanteServicio;
    
    @Autowired
    private BlindajeServicio blindajeServicio;
    
    
    public void crear (Fuente fuente, String idFuente, String idBlindaje){
        Fuente fuenteC = new Fuente();
        Fabricante fabricante = fabricanteServicio.buscarPorId(idFuente);
        Blindaje blindaje = blindajeServicio.buscarPorId(idBlindaje);
        
        fuenteC.setNumero_de_serie(fuente.getNumero_de_serie());
        fuenteC.setActividad_fabricacion(fuente.getActividad_fabricacion());
        fuenteC.setTasa_exposicion_fabricacion(fuente.getTasa_exposicion_fabricacion());
        fuenteC.setFecha_fabricación(fuente.getFecha_fabricación());
        
        fuenteC.setRadionucleido(fuente.getRadionucleido());
        fuenteC.setFabricante(fabricante);
        fuenteC.setBlindaje(blindaje);
        
        fuenteRepositorio.save(fuenteC);
    }
    
    public void modificar (Fuente fuente, String idFuente, String idBlindaje) {
        Fuente fuenteDB = fuenteRepositorio.getById(fuente.getId());
        
        Fabricante fabricante = fabricanteServicio.buscarPorId(idFuente);
        Blindaje blindaje = blindajeServicio.buscarPorId(idBlindaje);
        
        fuenteDB.setNumero_de_serie(fuente.getNumero_de_serie());
        fuenteDB.setActividad_fabricacion(fuente.getActividad_fabricacion());
        fuenteDB.setTasa_exposicion_fabricacion(fuente.getTasa_exposicion_fabricacion());
        fuenteDB.setFecha_fabricación(fuente.getFecha_fabricación());
        
        fuenteDB.setRadionucleido(fuente.getRadionucleido());
        fuenteDB.setFabricante(fabricante);
        fuenteDB.setBlindaje(blindaje);
        
        fuenteRepositorio.save(fuenteDB);    
    }
    
    public void eliminar(Fuente fuente){
        fuenteRepositorio.delete(fuente);
    }
    
    public List<Fuente> listarTodas(){
        return fuenteRepositorio.findAll();
    }
    
    public List<Fuente> listarPorFabricanteId(String fabricanteId){
        return fuenteRepositorio.buscarPorFabricanteId(fabricanteId);
    }
    
    public List<Fuente> listarPorBlindajeId(String blindajeId){
        return fuenteRepositorio.buscarPorBlindajeId(blindajeId);
    }
    
    public List<Fuente> listarPorFabricanteNombre(String nombre){
        return fuenteRepositorio.buscarPorFabricanteNombre(nombre);
    }
    
    
    
}
