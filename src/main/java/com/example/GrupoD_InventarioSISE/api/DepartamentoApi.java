/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.api;

import com.example.GrupoD_InventarioSISE.iservice.IDepartamentoService;
import com.example.GrupoD_InventarioSISE.model.Departamento;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 *
 * @author RANDY
 */
@RestController
@RequestMapping("/api/departamento")
public class DepartamentoApi {
    
    @Autowired
    private IDepartamentoService iDepartamentoService;
    
    @GetMapping
    public Page<Departamento> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(value = "search", required = false) String search
            ) {
        return iDepartamentoService.paginado(search, PageRequest.of(page, size));
    }

    @GetMapping("/listartodo")
    public Page<Departamento> listartodo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "search", required = false) String search
            ) {
        return iDepartamentoService.paginado(search, PageRequest.of(page, size));
        }

    @GetMapping("/{id}")
        public Departamento listarPorId(@PathVariable Long id) {
            return iDepartamentoService.listarPorId(id);
        }
    
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Departamento departamento){
        try {
            iDepartamentoService.guardar(departamento);
            return ResponseEntity.status(HttpStatus.CREATED).body("Departamento guardado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el departamento: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Departamento departamento) {
        try {
            Departamento departamentoExistente = iDepartamentoService.listarPorId(id);
            departamento.setId(departamentoExistente.getId()); // Asegúrate de que el ID del departamento se establezca correctamente
            iDepartamentoService.actualizar(departamento);
            return ResponseEntity.ok("Departamento actualizado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el departamento: " + e.getMessage());
        }
    }
    
     @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            iDepartamentoService.eliminar(id);
            return ResponseEntity.ok("Departamento eliminado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el departamento: " + e.getMessage());
        }
    }
}
