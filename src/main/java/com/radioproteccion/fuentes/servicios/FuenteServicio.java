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
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    
    @Transactional(rollbackFor = Exception.class)
    public void crear (Fuente fuente, String idFuente, String idBlindaje){
        Fuente fuenteC = new Fuente();
        Fabricante fabricante = fabricanteServicio.buscarPorId(idFuente);
        Blindaje blindaje = blindajeServicio.buscarPorId(idBlindaje);
        
        fuenteC.setNumero_de_serie(fuente.getNumero_de_serie());
        fuenteC.setActividad_fabricacion(fuente.getActividad_fabricacion());
        fuenteC.setTasa_exposicion_fabricacion(fuente.getTasa_exposicion_fabricacion());
        fuenteC.setFecha_fabricacion(fuente.getFecha_fabricacion());
        
        fuenteC.setRadionucleido(fuente.getRadionucleido());
        fuenteC.setFabricante(fabricante);
        fuenteC.setBlindaje(blindaje);
        
        fuenteC.setPrestada(false);
        
        fuenteRepositorio.save(fuenteC);
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    public void modificar (String fuenteId, Fuente fuente, String fabricanteId, String blindajeId) {
        Fuente fuenteDB = fuenteRepositorio.getById(fuenteId);
        
        Fabricante fabricante = fabricanteServicio.buscarPorId(fabricanteId);
        Blindaje blindaje = blindajeServicio.buscarPorId(blindajeId);
        
        fuenteDB.setNumero_de_serie(fuente.getNumero_de_serie());
        fuenteDB.setActividad_fabricacion(fuente.getActividad_fabricacion());
        fuenteDB.setTasa_exposicion_fabricacion(fuente.getTasa_exposicion_fabricacion());
        fuenteDB.setFecha_fabricacion(fuente.getFecha_fabricacion());
        
        fuenteDB.setRadionucleido(fuente.getRadionucleido());
        fuenteDB.setFabricante(fabricante);
        fuenteDB.setBlindaje(blindaje);
        
        fuenteRepositorio.save(fuenteDB);    
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(String fuenteId){
        fuenteRepositorio.deleteById(fuenteId);
    }
    
    
    @Transactional(readOnly = true)
    public Fuente buscarPorId(String id){
        return fuenteRepositorio.findById(id).get();
    }
    
    
    @Transactional(readOnly = true)
    public List<Fuente> listarTodas(){
        return fuenteRepositorio.findAll();
    }
    
    
    @Transactional(readOnly = true)
    public List<Fuente> listarPorFabricanteId(String fabricanteId){
        return fuenteRepositorio.buscarPorFabricanteId(fabricanteId);
    }
    
    
    @Transactional(readOnly = true)
    public List<Fuente> listarPorBlindajeId(String blindajeId){
        return fuenteRepositorio.buscarPorBlindajeId(blindajeId);
    }
    
    
    @Transactional(readOnly = true)
    public List<Fuente> listarPorFabricanteNombre(String nombreFabricante){
        return fuenteRepositorio.buscarPorFabricanteNombre(nombreFabricante);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void cambiarEstado(String id){
        Fuente fuenteDB = fuenteRepositorio.getById(id);
        
        if(fuenteDB.getPrestada()){
            fuenteDB.setPrestada(Boolean.FALSE);
        } else {
            fuenteDB.setPrestada(Boolean.TRUE);
        }
        
        fuenteRepositorio.save(fuenteDB);
    }
    
    public double calcularActividad(Fuente fuente){
        
        Float actividad_inicial = fuente.getActividad_fabricacion();
        Double semiperiodo = fuente.getRadionucleido().getSemiperiodo();
        
        Date fecha_inicial = fuente.getFecha_fabricacion();
        Date fecha_actual = new Date();
        
        long diferencia = Math.abs(fecha_actual.getTime() - fecha_inicial.getTime());
        long diferencia_anios = TimeUnit.DAYS.convert(diferencia, TimeUnit.MILLISECONDS)/365;
                
        
        return actividad_inicial * Math.exp(-Math.log(2) * diferencia_anios / semiperiodo);
        
    }
    
    public double calcularExposicionActual(Fuente fuente){
        
        double actividad_actual = calcularActividad(fuente);
        
        return actividad_actual * fuente.getRadionucleido().getConstante_gamma();
        
    }
        
    
}
