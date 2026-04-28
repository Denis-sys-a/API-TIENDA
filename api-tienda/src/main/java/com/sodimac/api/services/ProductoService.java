package com.sodimac.api.services;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sodimac.api.models.ProductoModel;
import com.sodimac.api.repositories.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public ArrayList<ProductoModel> obtenerProductos() {
        return (ArrayList<ProductoModel>) productoRepository.findAll();
    }

    public ProductoModel guardarProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    public Optional<ProductoModel> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public ProductoModel actualizarProducto(Long id, ProductoModel request) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(request.getNombre());
            producto.setDescripcion(request.getDescripcion());
            producto.setMarca(request.getMarca());
            producto.setPrecioNormal(request.getPrecioNormal());
            producto.setPrecioTarjetaCmr(request.getPrecioTarjetaCmr());
            producto.setUrlImagen(request.getUrlImagen());
            producto.setCategoria(request.getCategoria());
            return productoRepository.save(producto);
        }).orElse(null);
    }

    public boolean eliminarProducto(Long id) {
        try {
            productoRepository.deleteById(id);
            return true;
        } catch(Exception err) {
            return false;
        }
    }
}