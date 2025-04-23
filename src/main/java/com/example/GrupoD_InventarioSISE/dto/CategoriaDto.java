/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.dto;

/**
 *
 * @author RANDY
 */
public class CategoriaDto {
    
    private long Id;
    private String nombre_departamento;
    private String nombre;
    private String imagen_url;

    public CategoriaDto() {
    }

    public CategoriaDto(long Id, String nombre_departamento, String nombre, String imagen_url) {
        this.Id = Id;
        this.nombre_departamento = nombre_departamento;
        this.nombre = nombre;
        this.imagen_url = imagen_url;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    
}
