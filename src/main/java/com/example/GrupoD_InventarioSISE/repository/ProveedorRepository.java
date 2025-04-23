/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.repository;

import com.example.GrupoD_InventarioSISE.model.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author RANDY
 */
public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{
        
    @Query("SELECT p FROM Proveedor p WHERE " +
            "LOWER(p.nombre_comercial) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.tipodocumento.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.numero_documento) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.telefono) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.correo) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Proveedor> paginarProveedores(@Param("search") String search, Pageable pageable);

    @Query("SELECT p FROM Proveedor p WHERE p.estado_auditoria = true ")
    Page<Proveedor> findAllActive(Pageable Pageable);
}
