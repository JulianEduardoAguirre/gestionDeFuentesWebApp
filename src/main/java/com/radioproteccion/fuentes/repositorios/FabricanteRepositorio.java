/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.repositorios;

import com.radioproteccion.fuentes.entidades.Fabricante;
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
public interface FabricanteRepositorio extends JpaRepository<Fabricante, String>{
    
    @Query("SELECT f FROM Fabricante f WHERE f.nombre LIKE :nombre")
    public List<Fabricante> buscarPorNombre(@Param("nombre") String nombre);
}
