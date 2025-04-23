/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GrupoD_InventarioSISE.model;

import jakarta.persistence.*;

/**
 *
 * @author RANDY
 */
@Entity
@Table(name = "gd_categoria")
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private long iddepartamento;
    private String nombre;
    private String imagen_url;
    private boolean estado_auditoria = true;

    @ManyToOne
    @JoinColumn(name = "iddepartamento", nullable = false, insertable = false, updatable = false)
    private Departamento departamento;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public long getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(long iddepartamento) {
        this.iddepartamento = iddepartamento;
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

    public boolean isEstado_auditoria() {
        return estado_auditoria;
    }

    public void setEstado_auditoria(boolean estado_auditoria) {
        this.estado_auditoria = estado_auditoria;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    
}
