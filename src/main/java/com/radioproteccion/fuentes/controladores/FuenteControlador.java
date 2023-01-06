/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radioproteccion.fuentes.controladores;

import com.radioproteccion.fuentes.entidades.Blindaje;
import com.radioproteccion.fuentes.entidades.Fabricante;
import com.radioproteccion.fuentes.entidades.Fuente;
import com.radioproteccion.fuentes.servicios.BlindajeServicio;
import com.radioproteccion.fuentes.servicios.FabricanteServicio;
import com.radioproteccion.fuentes.servicios.FuenteServicio;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jaguirre89
 */
@Controller
@RequestMapping("/fuente")
public class FuenteControlador {
    
    @Autowired
    private FuenteServicio fuenteServicio;
    
    @Autowired
    private FabricanteServicio fabricanteServicio;
    
    @Autowired
    private BlindajeServicio blindajeServicio;
    
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Fuente> fuentes = fuenteServicio.listarTodas();
        modelo.put("fuentes", fuentes);
        
        return "fuente-listado.html";
    }
    
    @GetMapping("/agregar")
    public String agregar(ModelMap modelo){
        
        List<Fabricante> fabricantes = fabricanteServicio.listarTodos();
        List<Blindaje> blindajes = blindajeServicio.listarTodos();
        
        
        modelo.put("fuente", new Fuente());
        modelo.put("fabricantes", fabricantes);
        modelo.put("blindajes", blindajes);
        
        
        return "fuente-formulario.html";
    }
    
    @PostMapping("/agregar")
    public String agregar(@Valid Fuente fuente, BindingResult bindingResult,
            @RequestParam("fabricanteId") String fabricanteId, 
            @RequestParam("blindajeId") String blindajeid,
            RedirectAttributes attr, ModelMap modelo){
        
        if(bindingResult.hasErrors()){
            modelo.put("fuente", fuente);
            return "fuente-formulario.html";
        }
        
        fuenteServicio.crear(fuente, blindajeid, blindajeid);
        attr.addFlashAttribute("exito", "Fuente creada con éxito");
        return "redirect:/fuente/lista";
        
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        List<Fabricante> fabricantes = fabricanteServicio.listarTodos();
        List<Blindaje> blindajes = blindajeServicio.listarTodos();
        
        Fuente fuenteDB = fuenteServicio.buscarPorId(id);
        
        modelo.put("fuente", fuenteDB);
        modelo.put("fabricantes", fabricantes);
        modelo.put("blindajes", blindajes);      
        
        return "fuente-formulario.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @Valid Fuente fuente,
            BindingResult bindingResult,
            @RequestParam String fabricanteId,
            @RequestParam String blindajeId,
            RedirectAttributes attr, ModelMap modelo){
        
        if(bindingResult.hasErrors()){
            modelo.put("fuente", fuente);
            return "fuente-formulario.html";
        }
        
        fuenteServicio.modificar(id, fuente, fabricanteId, blindajeId);
        attr.addFlashAttribute("exito", "Fuente modificada con éxito");
        return "redirect:/fuente/lista";
        
    }
    
    
    
    
}
