package com.sodimac.api.controllers;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sodimac.api.models.ProductoModel;
import com.sodimac.api.services.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping()
    public ArrayList<ProductoModel> obtenerProductos() {
        return productoService.obtenerProductos();
    }

    @PostMapping()
    public ProductoModel guardarProducto(@RequestBody ProductoModel producto) {
        return this.productoService.guardarProducto(producto);
    }

    @GetMapping(path = "/{id}")
    public Optional<ProductoModel> obtenerProductoPorId(@PathVariable("id") Long id) {
        return this.productoService.obtenerPorId(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductoModel> actualizarProducto(@PathVariable("id") Long id, @RequestBody ProductoModel producto) {
        ProductoModel actualizado = this.productoService.actualizarProducto(id, producto);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.productoService.eliminarProducto(id);
        if (ok) {
            return "Se elimino el producto con id " + id;
        } else {
            return "No pudo eliminar el producto con id " + id;
        }
    }
}