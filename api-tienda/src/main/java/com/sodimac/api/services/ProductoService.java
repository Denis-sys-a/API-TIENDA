package com.sodimac.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sodimac.api.models.ProductoModel;
import com.sodimac.api.repositories.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<ProductoModel> obtenerProductos() {
        return (List<ProductoModel>) productoRepository.findAll();
    }

    public ProductoModel guardarProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    public Optional<ProductoModel> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public ProductoModel actualizarProducto(Long id, ProductoModel request) {
        Optional<ProductoModel> encontrado = productoRepository.findById(id);
        if (encontrado.isEmpty())
            return null;
        ProductoModel producto = encontrado.get();
        if (request.getNombre() != null)
            producto.setNombre(request.getNombre());
        if (request.getDescripcion() != null)
            producto.setDescripcion(request.getDescripcion());
        if (request.getMarca() != null)
            producto.setMarca(request.getMarca());
        if (request.getPrecioNormal() != null)
            producto.setPrecioNormal(request.getPrecioNormal());
        if (request.getPrecioTarjetaCmr() != null)
            producto.setPrecioTarjetaCmr(request.getPrecioTarjetaCmr());
        if (request.getUrlImagen() != null)
            producto.setUrlImagen(request.getUrlImagen());
        if (request.getCategoria() != null)
            producto.setCategoria(request.getCategoria());
        return productoRepository.save(producto);
    }

    public boolean eliminarProducto(Long id) {
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    public List<ProductoModel> obtenerPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoria_Id(categoriaId);
    }

    public List<ProductoModel> obtenerPorMarca(String marca) {
        return productoRepository.findByMarcaIgnoreCase(marca);
    }

    public List<ProductoModel> buscarPorNombre(String texto) {
        return productoRepository.findByNombreContainingIgnoreCase(texto);
    }

    public List<ProductoModel> obtenerOfertas() {
        return productoRepository.findByPrecioTarjetaCmrLessThan(999999.0);
    }

}