/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.servicios;

import com.radioproteccion.fuentes.entidades.Usuario;
import com.radioproteccion.fuentes.repositorios.UsuarioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aguir
 */
public class UsuarioServicio {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    
    @Transactional(rollbackFor = Exception.class)
    public void crear(Usuario usuario, String password, String password2){
        
        //Agregar los setters de la contraseña junto con el encriptador
        usuarioRepositorio.save(usuario);
    }
    
    
    @Transactional(readOnly = true)
    public Usuario buscarPorId(String id){
        return usuarioRepositorio.getById(id);
    }
    
    
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos(){
        return usuarioRepositorio.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> listatPorNombre(String nombre){
        return usuarioRepositorio.buscarPorNombre(nombre);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void modificar(String id, Usuario usuario){
        Usuario usuarioDB = buscarPorId(id);
        
        usuarioDB.setNombre(usuario.getNombre());
        usuarioDB.setApellido(usuario.getApellido());
        usuarioDB.setSeccion(usuario.getSeccion());
        
        usuarioRepositorio.save(usuarioDB);
        
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void modificarPassword(String id, String password, String password2){
        Usuario usuarioDB = buscarPorId(id);
        
        usuarioDB.setPassword(password);
        
        usuarioRepositorio.save(usuarioDB);
       
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(String id){
        usuarioRepositorio.deleteById(id);
    }
    
    
}
