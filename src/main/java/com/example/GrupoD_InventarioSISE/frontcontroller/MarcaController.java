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
@RequestMapping("/marca")
public class MarcaController {
    
    @GetMapping
    public String mantenimiento() {
        return "marca/mantenimiento-marca";
    }

    @GetMapping("/nuevo")
    public String nuevoMarca( Model model) {
        model.addAttribute("titulo", "Nuevo Marca");
        model.addAttribute("accion", "nuevo");
        return "marca/form-marca";
    }
    
    @GetMapping("/editar/{id}")
    public String editarMarca(@PathVariable Long id, Model model) {
        model.addAttribute("titulo", "Editar Marca");
        model.addAttribute("accion", "editar");
        model.addAttribute("id", id);
        return "marca/form-marca";
    }
}
