/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.controladores;
import com.radioproteccion.fuentes.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author jaguirre89
 */
@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
    

}
