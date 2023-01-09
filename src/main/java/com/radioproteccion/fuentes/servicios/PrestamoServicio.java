/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.servicios;

import com.radioproteccion.fuentes.entidades.Fuente;
import com.radioproteccion.fuentes.entidades.Prestamo;
import com.radioproteccion.fuentes.entidades.Usuario;
import com.radioproteccion.fuentes.repositorios.PrestamoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aguir
 */
@Service
public class PrestamoServicio {
    
    @Autowired
    private PrestamoRepositorio prestamoRepositorio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private FuenteServicio fuenteServicio;
    
    
    @Transactional(rollbackFor = Exception.class)
    public void pedirFuente(String usuarioId, String fuenteId) throws Exception{
        
        Usuario usuario = usuarioServicio.buscarPorId(fuenteId);
        Fuente fuente = fuenteServicio.buscarPorId(fuenteId);
        
        if(fuente.getPrestada()){
            throw new Exception("Fuente no disponible.");
        }
        
        Prestamo prestamo = new Prestamo();
        
        prestamo.setActivo(Boolean.TRUE);
        prestamo.setUsuario(usuario);
        prestamo.setFuente(fuente);
        
        fuenteServicio.cambiarEstado(fuenteId);
        
        prestamoRepositorio.save(prestamo);
        
    }
    
    @Transactional(readOnly = true)
    public List<Prestamo> listarTodos(){
        return prestamoRepositorio.findAll();
    }
    
    
    @Transactional(readOnly = true)
    public List<Prestamo> listarActivos(){
        return prestamoRepositorio.buscarActivos();
    }

    @Transactional(readOnly = true)
    public List<Prestamo> listarActivosPropios(String id){
        return prestamoRepositorio.buscarActivosPropios(id);
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    public void devolverFuente(String prestamoId){
        Prestamo prestamo = prestamoRepositorio.getById(prestamoId);
        Fuente fuente = prestamo.getFuente();
        fuenteServicio.cambiarEstado(fuente.getId());
        
        prestamo.setActivo(Boolean.FALSE);
        
        prestamoRepositorio.save(prestamo);
        
    }
    
    
    
}
