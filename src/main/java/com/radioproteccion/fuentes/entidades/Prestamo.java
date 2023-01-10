/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author aguir
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prestamo {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")    
    private String id;
        
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    
    private Boolean activo;
    
    @OneToOne
    private Fuente fuente;
    
    @ManyToOne
    private Usuario usuario;

//    public Prestamo() {
//    }
//
//    public String getId() {
//        return id;
//    }
//
////    public void setId(String id) {
////        this.id = id;
////    }
//
//    public Date getFechaInicio() {
//        return fechaInicio;
//    }
//
//    public void setFechaInicio(Date fechaInicio) {
//        this.fechaInicio = fechaInicio;
//    }
//
//    public Date getFechaFin() {
//        return fechaFin;
//    }
//
//    public void setFechaFin(Date fechaFin) {
//        this.fechaFin = fechaFin;
//    }
//
//    public Boolean getActivo() {
//        return activo;
//    }
//
//    public void setActivo(Boolean activo) {
//        this.activo = activo;
//    }
//
//    public Fuente getFuente() {
//        return fuente;
//    }
//
//    public void setFuente(Fuente fuente) {
//        this.fuente = fuente;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
    
    
}
