/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.controladores;

import com.radioproteccion.fuentes.entidades.Blindaje;
import com.radioproteccion.fuentes.servicios.BlindajeServicio;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/blindaje")
public class BlindajeControlador {
    
    @Autowired
    private BlindajeServicio blindajeServicio;
    
    
    @GetMapping("/lista")
    public String listarTodos(ModelMap modelo){
        List<Blindaje> blindajes = blindajeServicio.listarTodos();
        modelo.put("blindajes", blindajes);
        
        return "blindaje-listado.html";
    }
    
    
    @GetMapping("/agregar")
    public String agregar(ModelMap modelo){
        modelo.put("blindaje", new Blindaje());
        return "blindaje-formulario.html";
    }
    
    @PostMapping("/agregar")
    public String agregar(@Valid Blindaje blindaje, BindingResult bindingResult,
            RedirectAttributes attr, ModelMap modelo){
        
        if(bindingResult.hasErrors()){
            modelo.put("blindaje", blindaje);
            return "blindaje-formulario.html";
        }
        
        blindajeServicio.crear(blindaje);
        attr.addFlashAttribute("exito", "Blindaje agregado.");
        
        return "redirect:/blindaje/lista";
        
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        Blindaje blindajeDB = blindajeServicio.buscarPorId(id);
        modelo.put("blindaje", blindajeDB);
        
        return "blindaje-formulario.html";
        
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @Valid Blindaje blindaje,
            BindingResult bindingResult, RedirectAttributes attr, ModelMap modelo){
        
        if(bindingResult.hasErrors()){
            modelo.put("blindaje", blindaje);
            return "blindaje-formulario.html";
        }
        
        blindajeServicio.modificar(id, blindaje);
        attr.addFlashAttribute("exito", "Blindaje modificado exitósamente");
        return "redirect:/blindaje/lista";
        
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, RedirectAttributes attr){
        
        blindajeServicio.eliminar(id);
        attr.addFlashAttribute("exito", "Blindaje eliminado");
        return "redirect:/blindaje/lista";
        
    }
    
    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable("id") String id, ModelMap modelo){
        Blindaje blindaje = blindajeServicio.buscarPorId(id);
        modelo.put("blindaje", blindaje);
        
        return "blindaje-detalles.html";
    }
    
    
}
