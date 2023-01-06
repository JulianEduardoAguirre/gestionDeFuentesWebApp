/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.repositorios;

import com.radioproteccion.fuentes.entidades.Blindaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jaguirre89
 */
@Repository
public interface BlindajeRepositorio extends JpaRepository<Blindaje, String>{
    
}
