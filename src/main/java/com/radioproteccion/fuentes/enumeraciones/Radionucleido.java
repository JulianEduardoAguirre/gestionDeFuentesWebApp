/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.enumeraciones;

/**
 *
 * @author jaguirre89
 */
public enum Radionucleido {
    CESIO_137(3.5, 30.25), COBALTO_60(1.4, 5.04);
    
    private Double constante_gamma;
    private Double semiperiodo;

    private Radionucleido(Double constante_gamma, Double semiperiodo) {
        this.constante_gamma = constante_gamma;
        this.semiperiodo = semiperiodo;
    }

    public Double getConstante_gamma() {
        return constante_gamma;
    }

    public Double getSemiperiodo() {
        return semiperiodo;
    }
    
    
    
    
}
