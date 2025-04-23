/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.api;

import com.example.GrupoD_InventarioSISE.dto.ProductoDto;
import com.example.GrupoD_InventarioSISE.iservice.IProductoService;
import com.example.GrupoD_InventarioSISE.model.Producto;

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
@RequestMapping("/api/producto")
public class ProductoApi {

    @Autowired
    private IProductoService iProductoService;

    @GetMapping("/listar")
    public Page<ProductoDto> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "search", required = false) String search) {
        return iProductoService.Paginado(search, PageRequest.of(page, size));
    }

    @GetMapping("/listar-subcategoria/{id}")
    public Page<ProductoDto> listarPorSubCategoria(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(value = "search", required = false) String search) {
        return iProductoService.listarDtoPorSubCategoria(id, search, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public ProductoDto obtenerPorId(@PathVariable Long id) {
        return iProductoService.obtenerDtoPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            iProductoService.eliminar(id);
            return ResponseEntity.ok().body("Producto eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al eliminar el producto: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Producto producto) {
        try {
            iProductoService.guardar(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear el producto: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto productoExistente = iProductoService.obtenerPorId(id);
            producto.setId(productoExistente.getId()); // Asegurarse de que el ID sea el correcto
            iProductoService.actualizar(producto);
            return ResponseEntity.ok().body("Producto actualizado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @GetMapping("/obtener-completo/{id}")
    public ResponseEntity<Producto> obtenerProductoCompleto(@PathVariable Long id) {
        try {
            Producto producto = iProductoService.obtenerPorId(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    
}
