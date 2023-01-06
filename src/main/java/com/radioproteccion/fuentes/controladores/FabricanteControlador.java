/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.controladores;

import com.radioproteccion.fuentes.entidades.Fabricante;
import com.radioproteccion.fuentes.servicios.FabricanteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jaguirre89
 */
@Controller
@RequestMapping("/fabricante")
public class FabricanteControlador {
    
    @Autowired
    private FabricanteServicio fabricanteServicio;
    
    @GetMapping("/lista")
    public String listarTodos(ModelMap modelo){
        List<Fabricante> fabricantes = fabricanteServicio.listarTodos();
        modelo.put("fabricantes", fabricantes);
        
        return "fabricante-listado.html";
    }
    
    
    @GetMapping("/agregar")
    public String agregarFabricante(ModelMap modelo){
        modelo.put("fabricante", new Fabricante());
        return "fabricante-formulario.html";
    }
    
    
    @PostMapping("/agregar")
    public String agregarFabricante(@Validated Fabricante fabricante, BindingResult bindingResult,
            ModelMap modelo, RedirectAttributes attr){
        
        if(bindingResult.hasErrors()){
            modelo.addAttribute("fabricante", fabricante);
            return "fabricante-formulario.html";
        }
        
        fabricanteServicio.crear(fabricante);
        attr.addFlashAttribute("exito", "Fabricante creado exitósamente.");
        return "redirect:/fabricante/lista";
           
    }
    
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") String id, ModelMap modelo){
        Fabricante fabricanteDB = fabricanteServicio.buscarPorId(id);
        modelo.put("fabricante", fabricanteDB);
        
        return "fabricante-formulario.html";
    }
    
    
    @PostMapping("modificar/{id}")
    public String modificar(@PathVariable String id, 
            @Validated Fabricante fabricante, BindingResult bindingResult,
            RedirectAttributes attr, ModelMap modelo){
        
        
        if(bindingResult.hasErrors()){
            modelo.put("fabricante", fabricante);
            return "fabricante-formulario.html";
        }
        
        fabricanteServicio.modificar(id, fabricante);
        attr.addFlashAttribute("exito", "Fabricante actualizado");
        
        return "redirect:/fabricante/lista";
    }
    
    @PostMapping("eliminar/{id}")
    public String eliminar(@PathVariable("id") String id, RedirectAttributes attr){

        fabricanteServicio.eliminar(id);
        attr.addFlashAttribute("exito", "Fuente eliminada");
        
        return "redirect:/fabricante/lista";
    }
}
