/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.api;

import com.example.GrupoD_InventarioSISE.dto.CategoriaDto;
import com.example.GrupoD_InventarioSISE.iservice.ICategoriaService;
import com.example.GrupoD_InventarioSISE.model.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



/**
 *
 * @author RANDY
 */
@RestController
@RequestMapping("/api/categoria")
public class CategoriaApi {
    
    @Autowired
    private ICategoriaService iCategoriaService;
    
    @GetMapping("/listar")
    public Page<CategoriaDto> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(value = "search", required = false) String search
    ) {
        return iCategoriaService.Paginado(search, PageRequest.of(page, size));
    }

    @GetMapping("/listartodo")
    public Page<CategoriaDto> listartodo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "search", required = false) String search
    ) {
        return iCategoriaService.Paginado(search, PageRequest.of(page, size));
    }
    
    @GetMapping("/listar-departamento/{id}")
    public Page<CategoriaDto> listarPorDepartamento(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(value = "search", required = false) String search
    ) {
        return iCategoriaService.listarDtoPorDepartamento(id, search, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public CategoriaDto listarPorId(@PathVariable Long id) {
        return iCategoriaService.obtenerDtoPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Categoria categoria) {
        try {
            iCategoriaService.guardar(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoria guardada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la categoria: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            Categoria categoriaExistente = iCategoriaService.obtenerPorId(id);
            categoria.setId(categoriaExistente.getId());
            iCategoriaService.actualizar(categoria);
            return ResponseEntity.ok("Categoria actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la categoria: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            iCategoriaService.eliminar(id);
            return ResponseEntity.ok("Categoria eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la categoria: " + e.getMessage());
        }
    }
}
