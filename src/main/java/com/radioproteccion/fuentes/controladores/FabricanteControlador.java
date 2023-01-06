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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String agregarFabricante(){
        return "fabricante-formulario.html";
    }
    
    @PostMapping("/agregar")
    public String agregarFabricante(@RequestBody Fabricante fabricante, RedirectAttributes attr){
        try {
            fabricanteServicio.crear(fabricante);
            attr.addFlashAttribute("exito", "Fabricante creado exitósamente.");
        } catch (Exception e) {
            attr.addFlashAttribute("error", "Error al crear el fabricante.");
        }
    
        return "redirect:/fabricante/lista";
    
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") String id, ModelMap modelo){
        Fabricante fabricanteDB = fabricanteServicio.buscarPorId(id);
        modelo.put("fabricante", fabricanteDB);
        
        return "fabricante-formulario.modificar.html";
    }
    
    @PostMapping("modificar/{id}")
    public String modificar(@RequestBody Fabricante fabricante, RedirectAttributes attr){
        
        fabricanteServicio.modificar(fabricante);
        
        return "redirect:/fabricante/lista";
    }
    
    @PostMapping("eliminar/{id")
    public String eliminar(@RequestParam("id") String id, RedirectAttributes attr){
        Fabricante fabricanteDB = fabricanteServicio.buscarPorId(id);
        try {
            fabricanteServicio.eliminar(fabricanteDB);
            attr.addFlashAttribute("exito", "Fabricante eliminado exitósamente");
        } catch (Exception e) {
            attr.addFlashAttribute("error", "No se pudo eliminar el fabricante");
        }
        
        return "redirect:/fabricante/lista";
    }
}
