/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.service;

import com.example.GrupoD_InventarioSISE.dto.ProductoDto;
import com.example.GrupoD_InventarioSISE.iservice.IProductoService;
import com.example.GrupoD_InventarioSISE.mapper.ProductoMapper;
import com.example.GrupoD_InventarioSISE.model.Producto;
import com.example.GrupoD_InventarioSISE.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author RANDY
 */
@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Page<ProductoDto> Paginado(String search, Pageable pageable) {
        Page<Producto> productos;
        if (search == null || search.isEmpty()) {
            productos = productoRepository.findAllActive(pageable);
        } else {
            productos = productoRepository.paginarProductos(search, pageable);
        }
        return ProductoMapper.toDtoList(productos, pageable);
    }

    @Override
    public Page<ProductoDto> listarDtoPorSubCategoria(Long idsubcategoria, String search, Pageable pageable) {
        if (search == null || search.isEmpty()) {
            search = "";
        }
        Page<Producto> productos = productoRepository.paginarProductosPorSubCategoria(idsubcategoria, search, pageable);
        return ProductoMapper.toDtoList(productos, pageable);
    }

    @Override
    public ProductoDto obtenerDtoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return ProductoMapper.toDto(producto);
    }

    @Override
    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        producto.setEstado_auditoria(false);
        productoRepository.save(producto);
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Override
    public void guardar(Producto producto) {
        producto.setEstado_auditoria(true);
        productoRepository.save(producto);
    }

    @Override
    public void actualizar(Producto producto) {
        productoRepository.findById(producto.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + producto.getId()));
        productoRepository.save(producto);
    }

    @Override
    public Page<ProductoDto> obtenerProductosStockBajo(int umbral, Pageable pageable) {
        Page<Producto> productos = productoRepository.findByStockLessThanEqualAndEstado_auditoriaTrue(umbral, pageable);
        return ProductoMapper.toDtoList(productos, pageable);
    }

    @Override
    public long contarProductosStockBajo(int umbral) {
        return productoRepository.countByStockLessThanEqualAndEstado_auditoriaTrue(umbral);
    }

}
