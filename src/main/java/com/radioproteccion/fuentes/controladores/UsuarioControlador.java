/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.controladores;

import com.radioproteccion.fuentes.entidades.Prestamo;
import com.radioproteccion.fuentes.entidades.Usuario;
import com.radioproteccion.fuentes.servicios.PrestamoServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author aguir
 */
@Controller
@RequestMapping("usuario")
public class UsuarioControlador {
    
    @Autowired
    private PrestamoServicio prestamoServicio;
    
    @GetMapping("/mis-prestamos")  
    public String misPrestamos(HttpSession session, ModelMap modelo){
        
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");   
        
        List<Prestamo> prestamos = prestamoServicio.listarActivosPropios(usuario.getId());
        boolean mi_var = false;
        if(prestamos.size() != 0){
            mi_var = true;
        }
        
        
        modelo.put("prestamos", prestamos);
        modelo.put("usuariosession", session);
        modelo.put("usuario", usuario);
        modelo.put("mi_var", mi_var);

        return "prestamos-lista.html";        
        
    }
    
    
    @PostMapping("/devolver/{id}")
    public String devolverPrestamo(@PathVariable("id") String prestamoId){
        
        prestamoServicio.devolverFuente(prestamoId);
        
        return "redirect:/usuario/mis-prestamos";
        
    }
    
    @PostMapping("/pedir/{id}")
    public String pedirPrestamo(@PathVariable("id") String fuenteId, HttpSession session){
        
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");  
        
        try {
            prestamoServicio.pedirFuente(usuario.getId(), fuenteId);
            
        } catch (Exception ex) {
            System.out.println("LA REMIL PUTISIMA MADRE");
        }
        
        return "redirect:/fuente/lista";
        
    }
    
    
}
