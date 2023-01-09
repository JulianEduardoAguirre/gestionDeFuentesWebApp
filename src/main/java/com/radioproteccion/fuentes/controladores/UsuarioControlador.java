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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        
        List<Prestamo> prestamos = prestamoServicio.listarTodos(); //prestamoServicio.listarActivosPropios(usuario.getId());
        
        modelo.put("prestamos", prestamos);
        modelo.put("usuariosession", session);
        modelo.put("usuario", usuario);

        return "prestamos-lista.html";        
        
    }
    
    
    @PostMapping("/devolver/{id}")
    public String devolverPrestamo(@RequestParam("id") String prestamoId){
        
        prestamoServicio.devolverFuente(prestamoId);
        
        return "redirect:/usuario/mis-prestamos";
        
    }
    
    @PostMapping("/pedir/{id}")
    public String pedirPrestamo(@PathVariable("id") String fuenteId, HttpSession session){
        
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");  
        String usuarioId = usuario.getId();
        
        try {
            prestamoServicio.pedirFuente(usuarioId, fuenteId);
            
        } catch (Exception ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:/fuente/lista";
        
    }
    
    
}
