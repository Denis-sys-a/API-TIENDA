package com.sodimac.api.controllers;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sodimac.api.models.ProductoModel;
import com.sodimac.api.services.ProductoService;

@Tag(name = "Productos", description = "Gestion de productos de la tienda")
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Listar todos los productos")
    @GetMapping()
    public ResponseEntity<List<ProductoModel>> obtenerProductos() {
        return ResponseEntity.ok(productoService.obtenerProductos());
    }

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping()
    public ResponseEntity<ProductoModel> guardarProducto(@Valid @RequestBody ProductoModel producto) {
        return ResponseEntity.ok(productoService.guardarProducto(producto));
    }

    @Operation(summary = "Obtener un producto por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoModel> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoModel producto) {

        ProductoModel actualizado = productoService.actualizarProducto(id, producto);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un producto por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        boolean ok = productoService.eliminarProducto(id);

        if (ok) {
            return ResponseEntity.ok("Producto eliminado correctamente");
        } else {
            return ResponseEntity.status(404).body("No se pudo eliminar el producto");
        }
    }

    @Operation(summary = "Listar productos por categoria")
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoModel>> obtenerPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(productoService.obtenerPorCategoria(categoriaId));
    }

    @Operation(summary = "Listar productos por marca")
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<ProductoModel>> obtenerPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(productoService.obtenerPorMarca(marca));
    }

    @Operation(summary = "Listar productos en oferta, (precio CMR menor al precio normal)")
    @GetMapping("/ofertas")
    public ResponseEntity<List<ProductoModel>> obtenerOfertas() {
        return ResponseEntity.ok(productoService.obtenerOfertas());
    }

    @Operation(summary = "Buscar productos por nombre")
    @GetMapping("/buscar/{texto}")
    public ResponseEntity<List<ProductoModel>> buscarPorNombre(@PathVariable String texto) {
        return ResponseEntity.ok(productoService.buscarPorNombre(texto));
    }
}