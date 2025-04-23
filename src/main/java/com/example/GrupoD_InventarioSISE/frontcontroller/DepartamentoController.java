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
@RequestMapping("/departamento")
public class DepartamentoController {
    
    @GetMapping
    public String mantenimiento() {
        return "departamento/mantenimiento-departamento";
    }

    @GetMapping("/home")
    public String homedepartamento() {
        return "departamento/home-departamento";
    }

    @GetMapping("/nuevo")
    public String nuevoDepartamento(Model model) {
        model.addAttribute("titulo", "Nuevo Departamento");
        model.addAttribute("accion", "nuevo");
        return "departamento/form-departamento";
    }

    @GetMapping("/editar/{id}")
    public String editarDepartamento(@PathVariable Long id, Model model) {
        model.addAttribute("titulo", "Editar Departamento");
        model.addAttribute("accion", "editar");
        model.addAttribute("id", id);
        return "departamento/form-departamento";
    }
}
