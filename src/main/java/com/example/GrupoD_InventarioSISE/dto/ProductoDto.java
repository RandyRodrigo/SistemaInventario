/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.dto;

/**
 *
 * @author RANDY
 */
public class ProductoDto {
    
    private long Id;
    private String nombre_categoria;
    private String nombre_marca;
    private String nombre_proveedor;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String especificaciones;
    private float precio;
    private int stock;
    private String imagen_url;
    private String informacion_fabricante_url;

    public ProductoDto() {
    }

    public ProductoDto(long Id, String nombre_categoria, String nombre_marca, String nombre_proveedor, String codigo, String nombre, String descripcion, String especificaciones, float precio, int stock, String imagen_url, String informacion_fabricante_url) {
        this.Id = Id;
        this.nombre_categoria = nombre_categoria;
        this.nombre_marca = nombre_marca;
        this.nombre_proveedor = nombre_proveedor;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.especificaciones = especificaciones;
        this.precio = precio;
        this.stock = stock;
        this.imagen_url = imagen_url;
        this.informacion_fabricante_url = informacion_fabricante_url;
    }

    public String getInformacion_fabricante_url() {
        return informacion_fabricante_url;
    }

    public void setInformacion_fabricante_url(String informacion_fabricante_url) {
        this.informacion_fabricante_url = informacion_fabricante_url;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }
    
    
}
