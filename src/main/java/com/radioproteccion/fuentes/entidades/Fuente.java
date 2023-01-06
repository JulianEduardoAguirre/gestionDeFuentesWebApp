/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.entidades;

import com.radioproteccion.fuentes.enumeraciones.Radionucleido;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author jaguirre89
 */

@Entity
public class Fuente {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    
    private String numero_de_serie;
    private Float actividad_fabricacion;
    private Float tasa_exposicion_fabricacion;  //dX/dt0 a 1 metro
    
    @Temporal(TemporalType.DATE)
    private Date fecha_fabricación;
    
    @Enumerated(EnumType.STRING)
    private Radionucleido radionucleido;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Fabricante fabricante;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Blindaje blindaje;

    public Fuente() {
    }

    public String getId() {
        return id;
    }

    public String getNumero_de_serie() {
        return numero_de_serie;
    }

    public void setNumero_de_serie(String numero_de_serie) {
        this.numero_de_serie = numero_de_serie;
    }

    public Float getActividad_fabricacion() {
        return actividad_fabricacion;
    }

    public void setActividad_fabricacion(Float actividad_fabricacion) {
        this.actividad_fabricacion = actividad_fabricacion;
    }

    public Float getTasa_exposicion_fabricacion() {
        return tasa_exposicion_fabricacion;
    }

    public void setTasa_exposicion_fabricacion(Float tasa_exposicion_fabricacion) {
        this.tasa_exposicion_fabricacion = tasa_exposicion_fabricacion;
    }

    public Date getFecha_fabricación() {
        return fecha_fabricación;
    }

    public void setFecha_fabricación(Date fecha_fabricación) {
        this.fecha_fabricación = fecha_fabricación;
    }

    public Radionucleido getRadionucleido() {
        return radionucleido;
    }

    public void setRadionucleido(Radionucleido radionucleido) {
        this.radionucleido = radionucleido;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Blindaje getBlindaje() {
        return blindaje;
    }

    public void setBlindaje(Blindaje blindaje) {
        this.blindaje = blindaje;
    }
     
    
    
}
