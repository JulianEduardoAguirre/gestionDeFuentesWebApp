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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

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
    
    @NotBlank(message = "Ingrese el número de serie de la fuente")
    private String numero_de_serie;
    
    @NotNull(message = "Ingrese la actividad inicial")
    private Float actividad_fabricacion;
    
    @NotNull(message = "Ingrese la tasa de exposición inicial")
    private Float tasa_exposicion_fabricacion;  //dX/dt0 a 1 metro
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past
    @NotNull(message = "Ingrese la fecha de fabricación")
    private Date fecha_fabricacion;
    
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

    public Date getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(Date fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
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
