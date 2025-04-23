/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.frontcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

/**
 *
 * @author RANDY
 */
@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
    
    @GetMapping
    public String mantenimiento() {
        return "proveedor/mantenimiento-proveedor";
    }

    @GetMapping("/nuevo")
    public String nuevoProveedor(Model model) {
        model.addAttribute("titulo", "Nuevo Proveedor");
        model.addAttribute("accion", "nuevo");
        return "proveedor/form-proveedor";
    }

    @GetMapping("/editar/{id}")
    public String editarProveedor(@PathVariable Long id, Model model) {
        model.addAttribute("titulo", "Editar Proveedor");
        model.addAttribute("accion", "editar");
        model.addAttribute("id", id);
        return "proveedor/form-proveedor";
    }
}
