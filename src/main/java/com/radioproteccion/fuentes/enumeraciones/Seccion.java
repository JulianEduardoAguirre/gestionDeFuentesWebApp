/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.enumeraciones;

/**
 *
 * @author aguir
 */
public enum Seccion {
    SRP("Sección Radioprotección RA-6"), DPR("División Protección Radiológica"), OTRO("Usuario externo");
    
    private final String nombre_seccion;

    private Seccion(String nombre_seccion) {
        this.nombre_seccion = nombre_seccion;
    }
    
    
    
}
