/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author jaguirre89
 */
@Entity
public class Blindaje {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")    
    private String id;
    
    private String modelo;
    private Long altura;
    private Long radio;
    private Long peso;

    public Blindaje() {
    }

    public String getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getAltura() {
        return altura;
    }

    public void setAltura(Long altura) {
        this.altura = altura;
    }

    public Long getRadio() {
        return radio;
    }

    public void setRadio(Long radio) {
        this.radio = radio;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }
    
    
    
}
