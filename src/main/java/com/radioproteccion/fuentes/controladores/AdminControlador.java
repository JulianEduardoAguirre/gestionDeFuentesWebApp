/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.controladores;

import com.radioproteccion.fuentes.entidades.Usuario;
import com.radioproteccion.fuentes.servicios.UsuarioServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @author aguir
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
public class AdminControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/usuario/lista")
    public String listarUsuarios(ModelMap modelo){
        
        List<Usuario> usuarios = usuarioServicio.listarTodos();
        
        modelo.put("usuarios", usuarios);
        
        return "usuario-lista.html";
        
    }
    
    
    @GetMapping("/usuario/registro")
    public String registro(ModelMap modelo){
        modelo.put("usuario", new Usuario());
        return "usuario-formulario.html";
    }
    
    
    @PostMapping("/usuario/registro")
    public String registro(@Valid Usuario usuario, BindingResult bindingResult,
            @RequestParam("password") String password,
            @RequestParam("password2") String password2,
            ModelMap modelo, RedirectAttributes attr){
        
        if (bindingResult.hasErrors()) {
            modelo.put("usuario", usuario);
            return "usuario-formulario.html";
        }
        
        try {
            usuarioServicio.crear(usuario, password, password2);
        } catch (Exception ex) {
            modelo.put("usuario", usuario);
            modelo.put("error", ex.getMessage());
            return "usuario-formulario.html";
        }
        
        return "redirect:/";
        
    }
    
        
    
}
