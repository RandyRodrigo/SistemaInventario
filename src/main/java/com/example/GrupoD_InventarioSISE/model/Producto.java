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
@Table(name = "gd_producto")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private long idsubcategoria;
    private long idmarca;
    private long idproveedor;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String especificaciones;
    private float precio;
    private int stock;
    private String imagen_url;
    private String informacion_fabricante_url;
    private boolean estado_auditoria = true;
    
    @ManyToOne
    @JoinColumn(name = "idsubcategoria", nullable = false, insertable = false, updatable = false)
    private SubCategoria subcategoria;
    
    @ManyToOne
    @JoinColumn(name = "idmarca", nullable = false, insertable = false, updatable = false)
    private Marca marca;
    
    @ManyToOne
    @JoinColumn(name = "idproveedor", nullable = false, insertable = false, updatable = false)
    private Proveedor proveedor;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public long getIdsubcategoria() {
        return idsubcategoria;
    }

    public void setIdsubcategoria(long idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
    }

    public long getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(long idmarca) {
        this.idmarca = idmarca;
    }

    public long getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(long idproveedor) {
        this.idproveedor = idproveedor;
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

    public String getInformacion_fabricante_url() {
        return informacion_fabricante_url;
    }

    public void setInformacion_fabricante_url(String informacion_fabricante_url) {
        this.informacion_fabricante_url = informacion_fabricante_url;
    }

    public boolean isEstado_auditoria() {
        return estado_auditoria;
    }

    public void setEstado_auditoria(boolean estado_auditoria) {
        this.estado_auditoria = estado_auditoria;
    }

    public SubCategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(SubCategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
}
