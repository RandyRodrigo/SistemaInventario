/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.api;

import com.example.GrupoD_InventarioSISE.dto.ProveedorDto;
import com.example.GrupoD_InventarioSISE.iservice.IProveedorService;
import com.example.GrupoD_InventarioSISE.model.Proveedor;

import org.apache.catalina.connector.Response;
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
@RequestMapping("/api/proveedor")
public class ProveedorApi {
    
    @Autowired
    private IProveedorService iProveedorService;
    
    @GetMapping("/listar")
    public Page<ProveedorDto> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(value = "search", required = false) String search
    ) {
        return iProveedorService.Paginado(search, PageRequest.of(page, size));
    }

    @GetMapping("/listartodo")
    public Page<ProveedorDto> listartodo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "search", required = false) String search
    ) {
        return iProveedorService.Paginado(search, PageRequest.of(page, size));
    }

    @GetMapping("{id}")
    public ProveedorDto obtenerPorId(@PathVariable Long id) {
        return iProveedorService.obtenerDtoPorId(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            iProveedorService.eliminar(id);
            return ResponseEntity.ok("Proveedor eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body("Error al eliminar el proveedor: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Proveedor proveedor) {
        try {
            iProveedorService.guardar(proveedor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Proveedor guardado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body("Error al guardar el proveedor: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        try {
            Proveedor proveedorExistente = iProveedorService.obtenerPorId(id);
            proveedor.setId(proveedorExistente.getId());
            iProveedorService.actualizar(proveedor);
            return ResponseEntity.ok("Proveedor actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body("Error al actualizar el proveedor: " + e.getMessage());
        }
    }
}
