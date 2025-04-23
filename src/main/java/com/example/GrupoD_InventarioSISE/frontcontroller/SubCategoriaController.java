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
@RequestMapping("/subcategoria")
public class SubCategoriaController {
    

    @GetMapping
    public String mantenimiento() {
        return "subcategoria/mantenimiento-subcategoria";
    }
    
    @GetMapping("{id}")
    public String listarSubCategorias(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", id);
        return "subcategoria/subcategorias-por-categoria";
    }

    @GetMapping("/nuevo")
    public String nuevaSubCategoria(Model model) {
        model.addAttribute("titulo", "Nueva SubCategoria");
        model.addAttribute("accion", "nuevo");
        return "subcategoria/form-subcategoria";
    }

    @GetMapping("/editar/{id}")
    public String editarSubCategoria(@PathVariable Long id, Model model) {
        model.addAttribute("titulo", "Editar SubCategoria");
        model.addAttribute("accion", "editar");
        model.addAttribute("id", id);
        return "subcategoria/form-subcategoria";
    }
}
