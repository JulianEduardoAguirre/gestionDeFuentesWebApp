/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.repositorios;

import com.radioproteccion.fuentes.entidades.Prestamo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aguir
 */
@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, String> {
    
    @Query("SELECT p FROM Prestamo p WHERE p.activo = true")
    public List<Prestamo> buscarActivos();
    
    @Query("SELECT p FROM Prestamo p WHERE p.usuario.id = :id AND p.activo = true")
    public List<Prestamo> buscarActivosPropios(@Param("id") String id);
    
}
