package com.sodimac.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sodimac.api.models.ProductoModel;
import com.sodimac.api.services.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping()
    public ResponseEntity<List<ProductoModel>> obtenerProductos() {
        return ResponseEntity.ok(productoService.obtenerProductos());
    }

    @PostMapping()
    public ResponseEntity<ProductoModel> guardarProducto(@RequestBody ProductoModel producto) {
        return ResponseEntity.ok(productoService.guardarProducto(producto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoModel> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> actualizarProducto(
            @PathVariable Long id,
            @RequestBody ProductoModel producto) {

        ProductoModel actualizado = productoService.actualizarProducto(id, producto);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        boolean ok = productoService.eliminarProducto(id);

        if (ok) {
            return ResponseEntity.ok("Producto eliminado correctamente");
        } else {
            return ResponseEntity.status(404).body("No se pudo eliminar el producto");
        }
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoModel>> obtenerPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(productoService.obtenerPorCategoria(categoriaId));
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<ProductoModel>> obtenerPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(productoService.obtenerPorMarca(marca));
    }

    @GetMapping("/ofertas")
    public ResponseEntity<List<ProductoModel>> obtenerOfertas() {
        return ResponseEntity.ok(productoService.obtenerOfertas());
    }

    @GetMapping("/buscar/{texto}")
    public ResponseEntity<List<ProductoModel>> buscarPorNombre(@PathVariable String texto) {
        return ResponseEntity.ok(productoService.buscarPorNombre(texto));
    }
}