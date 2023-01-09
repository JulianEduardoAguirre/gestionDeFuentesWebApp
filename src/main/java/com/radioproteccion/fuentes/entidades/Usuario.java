/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.entidades;

import com.radioproteccion.fuentes.enumeraciones.Rol;
import com.radioproteccion.fuentes.enumeraciones.Seccion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author aguir
 */
@Entity
public class Usuario {
    
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotBlank(message = "Debe ingresar su nombre")
    private String nombre;
    
    @NotBlank(message = "Debe ingresar su apellido")
    private String apellido;
    
    @NotNull(message = "Ingrese su matrícula institucional")
    @Column(unique = true)
    private Long matricula;
    
    
    @NotBlank(message = "Debe ingresar su dirección de correo electrónico")
    @Email
    private String email;
    
    @NotBlank(message = "Debe ingresar una contraseña")
    @Size(min = 8, message = "La contraseña debe contener, al menos, 8 caracteres")
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Seccion seccion;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;


    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
}
