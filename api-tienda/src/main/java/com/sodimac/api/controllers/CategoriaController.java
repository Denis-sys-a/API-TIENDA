package com.sodimac.api.controllers;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sodimac.api.models.CategoriaModel;
import com.sodimac.api.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ArrayList<CategoriaModel> obtenerCategorias() {
        return categoriaService.obtenerCategorias();
    }

    @PostMapping
    public CategoriaModel guardarCategoria(@RequestBody CategoriaModel categoria) {
        return this.categoriaService.guardarCategoria(categoria);
    }

    @GetMapping(path = "/{id}")
    public Optional<CategoriaModel> obtenerCategoriaPorId(@PathVariable("id") Long id) {
        return this.categoriaService.obtenerPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.categoriaService.eliminarCategoria(id);
        if (ok) {
            return "Se eliminó la categoría con id " + id;
        } else {
            return "No se pudo eliminar la categoría con id " + id;
        }
    }
}