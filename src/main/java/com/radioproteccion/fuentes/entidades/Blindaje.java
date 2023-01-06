/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    
    @NotBlank(message = "Ingrese el nombre del blindaje")
    private String modelo;
    
    @NotNull(message = "Ingrese la altura del blindaje")
    private Long altura;
    
    @NotNull(message = "Ingrese el radio del blindake")
    private Long radio;

    @NotNull(message = "Ingrese el peso del blindaje")
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
