/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.api;

import com.example.GrupoD_InventarioSISE.dto.SubCategoriaDto;
import com.example.GrupoD_InventarioSISE.iservice.ISubCategoriaService;
import com.example.GrupoD_InventarioSISE.model.SubCategoria;

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
@RequestMapping("/api/subcategoria")
public class SubCategoriaApi {
    
    @Autowired
    private ISubCategoriaService iSubCategoriaService;
    
    @GetMapping("/listar")
    public Page<SubCategoriaDto> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "search", required = false) String search
    ) {
        return iSubCategoriaService.Paginado(search, PageRequest.of(page, size));
    }


    @GetMapping("/listartodo")
    public Page<SubCategoriaDto> listartodo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size,
            @RequestParam(value = "search", required = false) String search
    ) {
        return iSubCategoriaService.Paginado(search, PageRequest.of(page, size));
    }
    
    @GetMapping("/listar-categoria/{id}")
    public Page<SubCategoriaDto> listarPorCategoria(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(value = "search", required = false) String search
    ) {
        return iSubCategoriaService.listarDtoPorCategoria(id, search, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public SubCategoriaDto obtenerPorId(@PathVariable Long id) {
        return iSubCategoriaService.obtenerDtoPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody SubCategoria subcategoria) {
        try {
            iSubCategoriaService.guardar(subcategoria);
            return ResponseEntity.status(HttpStatus.CREATED).body("Subcategoría guardada con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la subcategoría: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody SubCategoria subcategoria) {
        try {
            SubCategoria subcategoriaExistente = iSubCategoriaService.obtenerPorId(id);
            subcategoria.setId(subcategoriaExistente.getId()); // Asegurarse de que el ID sea el correcto
            iSubCategoriaService.actualizar(subcategoria);
            return ResponseEntity.ok("Subcategoría actualizada con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la subcategoría: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            iSubCategoriaService.eliminar(id);
            return ResponseEntity.ok("Subcategoría eliminada con éxito.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la subcategoría: " + e.getMessage());
        }
    }
}
