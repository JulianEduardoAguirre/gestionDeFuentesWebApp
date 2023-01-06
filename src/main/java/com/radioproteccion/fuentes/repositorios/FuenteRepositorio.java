/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.repositorios;

import com.radioproteccion.fuentes.entidades.Fuente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jaguirre89
 */
@Repository
public interface FuenteRepositorio extends JpaRepository<Fuente, String> {
    
    @Query("SELECT f FROM Fuente f WHERE f.fabricante.id = :id")
    public List<Fuente> buscarPorFabricanteId(@Param("id") String id);
    
    @Query("SELECT f FROM Fuente f WHERE f.fabricante.nombre LIKE :nombre")
    public List<Fuente> buscarPorFabricanteNombre(@Param("nombre") String nombre);
    
    @Query("SELECT f FROM Fuente f WHERE f.blindaje.id = :id")
    public List<Fuente> buscarPorBlindajeId(@Param("id") String id);
    
}
