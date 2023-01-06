/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.repositorios;

import com.radioproteccion.fuentes.entidades.Blindaje;
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
public interface BlindajeRepositorio extends JpaRepository<Blindaje, String>{
    
    @Query("SELECT b FROM Blindaje b WHERE b.modelo = :modelo")
    public List<Blindaje> listarPorModelo(@Param("modelo") String modelo);
}
