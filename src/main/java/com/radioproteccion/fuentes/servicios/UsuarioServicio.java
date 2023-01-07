/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.servicios;

import com.radioproteccion.fuentes.entidades.Usuario;
import com.radioproteccion.fuentes.enumeraciones.Rol;
import com.radioproteccion.fuentes.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author aguir
 */
@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    
    @Transactional(rollbackFor = Exception.class)
    public void crear(Usuario usuario, String password, String password2){
        
        //Agregar los setters de la contraseña junto con el encriptador
        usuario.setRol(Rol.USER);
        
        String passwordEncriptado = new BCryptPasswordEncoder().encode(password);
        usuario.setPassword(passwordEncriptado);
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
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.findByEmail(email);

        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);

        } else {
            return null;
        }
    }
}
