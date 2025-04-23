/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.repository;

import com.example.GrupoD_InventarioSISE.model.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author RANDY
 */
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
    
    @Query("SELECT d FROM Departamento d WHERE " +
            "LOWER(d.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.descripcion) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.imagen_url) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Departamento> paginarDepartamentos(@Param("search") String search, Pageable pageable);

    @Query("SELECT d FROM Departamento d WHERE d.estado_auditoria = true ")
    Page<Departamento> findAllActive(Pageable Pageable);
}
