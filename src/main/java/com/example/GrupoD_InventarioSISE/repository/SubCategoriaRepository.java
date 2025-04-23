/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.repository;

import com.example.GrupoD_InventarioSISE.model.SubCategoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author RANDY
 */
public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long>{
    
    @Query("SELECT s FROM SubCategoria s WHERE " +
            "LOWER(s.categoria.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(s.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(s.imagen_url) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<SubCategoria> paginarSubCategorias(@Param("search") String search, Pageable pageable);
    
    @Query("SELECT s FROM SubCategoria s WHERE s.idcategoria = :idcategoria AND " +
            "(LOWER(s.categoria.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(s.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(s.imagen_url) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<SubCategoria> paginarSubCategoriasPorCategoria(@Param("idcategoria") Long idcategoria, @Param("search") String search, Pageable pageable);

    @Query("SELECT s FROM SubCategoria s WHERE s.estado_auditoria = true ")
    Page<SubCategoria> findAllActive(Pageable Pageable);
}
