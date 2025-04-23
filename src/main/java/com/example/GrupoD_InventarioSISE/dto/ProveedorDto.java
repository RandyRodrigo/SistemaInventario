/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.dto;

/**
 *
 * @author RANDY
 */
public class ProveedorDto {
    
    private long Id;
    private String nombre_comercial;
    private String nombre_documento;
    private String numero_documento;
    private String telefono;
    private String correo;

    public ProveedorDto() {
    }

    public ProveedorDto(long Id, String nombre_comercial, String nombre_documento, String numero_documento, String telefono, String correo) {
        this.Id = Id;
        this.nombre_comercial = nombre_comercial;
        this.nombre_documento = nombre_documento;
        this.numero_documento = numero_documento;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    public String getNombre_documento() {
        return nombre_documento;
    }

    public void setNombre_documento(String nombre_documento) {
        this.nombre_documento = nombre_documento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
