/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.iservice;

import com.example.GrupoD_InventarioSISE.dto.ProductoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.GrupoD_InventarioSISE.model.Producto;

/**
 *
 * @author RANDY
 */
public interface IProductoService {
    
    Page<ProductoDto> Paginado(String search, Pageable pageable);
    Page<ProductoDto> listarDtoPorSubCategoria(Long idsubcategoria, String search, Pageable pageable);    
    Page<ProductoDto> obtenerProductosStockBajo(int umbral, Pageable pageable);
    long contarProductosStockBajo(int umbral);
    ProductoDto obtenerDtoPorId(Long id);
    Producto obtenerPorId(Long id);
    void eliminar(Long id);
    void guardar(Producto producto);
    void actualizar(Producto producto);   
}
