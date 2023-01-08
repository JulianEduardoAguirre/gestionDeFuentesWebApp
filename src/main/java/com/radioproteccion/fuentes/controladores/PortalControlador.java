/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.controladores;

import com.radioproteccion.fuentes.entidades.Usuario;
import com.radioproteccion.fuentes.servicios.UsuarioServicio;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @GetMapping("/registro")
    public String registro(ModelMap modelo){
        modelo.put("usuario", new Usuario());
        return "registro-formulario.html";
    }
    
    
    @PostMapping("/registro")
    public String registro(@Valid Usuario usuario, BindingResult bindingResult,
            @RequestParam("password") String password,
            @RequestParam("password2") String password2,
            ModelMap modelo, RedirectAttributes attr){
        
        if (bindingResult.hasErrors()) {
            modelo.put("usuario", usuario);
            return "registro-formulario.html";
        }
        
        usuarioServicio.crear(usuario, password, password2);
        
        return "redirect:/";
        
        
        
    }
}
