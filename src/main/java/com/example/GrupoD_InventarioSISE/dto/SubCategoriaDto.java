/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.dto;

/**
 *
 * @author RANDY
 */
public class SubCategoriaDto {
    
    private long Id;
    private String nombre_categoria;
    private String nombre;
    private String imagen_url;

    public SubCategoriaDto() {
    }

    public SubCategoriaDto(long Id, String nombre_categoria, String nombre, String imagen_url) {
        this.Id = Id;
        this.nombre_categoria = nombre_categoria;
        this.nombre = nombre;
        this.imagen_url = imagen_url;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
