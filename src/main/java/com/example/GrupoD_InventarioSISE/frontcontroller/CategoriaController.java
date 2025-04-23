/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.frontcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author RANDY
 */
@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    
    @GetMapping
    public String mantenimiento() {
        return "categoria/mantenimiento-categoria";
    }
    
    @GetMapping("{id}")
    public String listarCategorias(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", id);
        return "categoria/categorias-por-departamento";
    }

    @GetMapping("/nuevo")
    public String nuevaCategoria(Model model) {
        model.addAttribute("titulo", "Nueva Categoria");
        model.addAttribute("accion", "nuevo");
        return "categoria/form-categoria";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Long id, Model model) {
        model.addAttribute("titulo", "Editar Categoria");
        model.addAttribute("accion", "editar");
        model.addAttribute("id", id);
        return "categoria/form-categoria";
    }
}
