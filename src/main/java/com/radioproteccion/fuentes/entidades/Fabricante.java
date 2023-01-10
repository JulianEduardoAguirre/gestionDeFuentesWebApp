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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author jaguirre89
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fabricante {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotBlank(message = "Ingrese el nombre del fabricante")
    private String nombre;
    
    @NotBlank(message = "Ingrese el teléfono del fabricante")
    private String telefono;
    
    
    @NotBlank(message = "Ingrese la dirección de contacto")
    private String contacto;

//    public Fabricante() {
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getTelefono() {
//        return telefono;
//    }
//
//    public void setTelefono(String telefono) {
//        this.telefono = telefono;
//    }
//
//    public String getContacto() {
//        return contacto;
//    }
//
//    public void setContacto(String contacto) {
//        this.contacto = contacto;
//    }
    
    
    
}
