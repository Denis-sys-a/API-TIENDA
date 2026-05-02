package com.sodimac.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "productos")
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotNull(message = "El precio normal es obligatorio")
    @Positive(message = "El precio normal debe ser mayor a 0")
    private Double precioNormal;

    @Positive(message = "El precio CMR debe ser mayor a 0")
    private Double precioTarjetaCmr;

    private String urlImagen;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaModel categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecioNormal() {
        return precioNormal;
    }

    public void setPrecioNormal(Double precioNormal) {
        this.precioNormal = precioNormal;
    }

    public Double getPrecioTarjetaCmr() {
        return precioTarjetaCmr;
    }

    public void setPrecioTarjetaCmr(Double precioTarjetaCmr) {
        this.precioTarjetaCmr = precioTarjetaCmr;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }
}