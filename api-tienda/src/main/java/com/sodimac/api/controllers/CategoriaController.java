package com.sodimac.api.controllers;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sodimac.api.models.CategoriaModel;
import com.sodimac.api.services.CategoriaService;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Tag(name = "Categorias", description = "Gestion de categorias de la tienda")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Listar todas las categorias")
    @GetMapping
    public ResponseEntity<List<CategoriaModel>> obtenerCategorias() {
        return ResponseEntity.ok(categoriaService.obtenerCategorias());
    }

    @Operation(summary = "Crear una nueva categoria")
    @PostMapping
    public ResponseEntity<CategoriaModel> guardarCategoria(@Valid @RequestBody CategoriaModel categoria) {
        CategoriaModel creada = categoriaService.guardarCategoria(categoria);
        return ResponseEntity.ok(creada);
    }

    @Operation(summary = "Obtener una categoria por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaModel> obtenerCategoriaPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar una categoria existente")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaModel> actualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaModel categoria) {

        CategoriaModel actualizada = categoriaService.actualizarCategoria(id, categoria);

        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una categoria por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {
        boolean ok = categoriaService.eliminarCategoria(id);
        if (ok) {
            return ResponseEntity.ok("Categoría eliminada con éxito");
        } else {
            return ResponseEntity.status(404).body("La categoría no existe o no pudo eliminarse");
        }
    }
}