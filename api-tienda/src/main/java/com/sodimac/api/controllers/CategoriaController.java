package com.sodimac.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sodimac.api.models.CategoriaModel;
import com.sodimac.api.services.CategoriaService;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaModel>> obtenerCategorias() {
        return ResponseEntity.ok(categoriaService.obtenerCategorias());
    }

    @PostMapping
    public ResponseEntity<CategoriaModel> guardarCategoria(@RequestBody CategoriaModel categoria) {
        CategoriaModel creada = categoriaService.guardarCategoria(categoria);
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaModel> obtenerCategoriaPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {
        boolean ok = categoriaService.eliminarCategoria(id);
        if (ok) {
            return ResponseEntity.ok("Categoría eliminada con éxito");
        } else {
            return ResponseEntity.status(404).body("La categoría no existe o no pudo eliminarse");
        }
    }
}