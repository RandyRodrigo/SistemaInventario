/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.api;

import com.example.GrupoD_InventarioSISE.iservice.IMarcaService;
import com.example.GrupoD_InventarioSISE.model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


/**
 *
 * @author RANDY
 */
@RestController
@RequestMapping("/api/marca")
public class MarcaApi {
    
    @Autowired
    private IMarcaService iMarcaService;
    
    @GetMapping("/listar")
    public Page<Marca> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(value = "search", required = false) String search
            ) {
        return iMarcaService.paginado(search, PageRequest.of(page, size));
    }

    @GetMapping("/listartodo")
    public Page<Marca> listartodo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "search", required = false) String search
            ) {
        return iMarcaService.paginado(search, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Marca listarPorId(@PathVariable Long id) {
        return iMarcaService.listarPorId(id);
    }
    
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Marca marca){
        try {
            iMarcaService.guardar(marca);
            return ResponseEntity.status(HttpStatus.CREATED).body("Marca guardada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la marca: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Marca marca) {
        try {
            Marca marcaExistente = iMarcaService.listarPorId(id);
            marca.setId(marcaExistente.getId());
            iMarcaService.actualizar(marca);
            return ResponseEntity.ok("Marca actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la marca: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            iMarcaService.eliminar(id);
            return ResponseEntity.ok("Marca eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la marca: " + e.getMessage());
        }
    }
}
