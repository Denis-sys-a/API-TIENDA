package com.sodimac.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String marca;
    private Double precioNormal;
    private Double precioTarjetaCmr;
    private String urlImagen;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaModel categoria;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public Double getPrecioNormal() { return precioNormal; }
    public void setPrecioNormal(Double precioNormal) { this.precioNormal = precioNormal; }

    public Double getPrecioTarjetaCmr() { return precioTarjetaCmr; }
    public void setPrecioTarjetaCmr(Double precioTarjetaCmr) { this.precioTarjetaCmr = precioTarjetaCmr; }

    public String getUrlImagen() { return urlImagen; }
    public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }

    public CategoriaModel getCategoria() { return categoria; }
    public void setCategoria(CategoriaModel categoria) { this.categoria = categoria; }
}