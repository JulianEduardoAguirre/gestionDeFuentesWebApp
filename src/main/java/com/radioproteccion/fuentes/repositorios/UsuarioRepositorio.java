/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.repositorios;

import com.radioproteccion.fuentes.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author aguir
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{

    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre")
    public List<Usuario> buscarPorNombre(@Param("nombre") String nombre);
    
    public Usuario findByEmail(String email);
    
}
