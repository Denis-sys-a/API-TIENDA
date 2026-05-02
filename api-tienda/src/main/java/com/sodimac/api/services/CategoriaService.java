package com.sodimac.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodimac.api.models.CategoriaModel;
import com.sodimac.api.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaModel> obtenerCategorias() {
        return categoriaRepository.findAll();
    }

    public CategoriaModel guardarCategoria(CategoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<CategoriaModel> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public boolean eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id))
            return false;
        categoriaRepository.deleteById(id);
        return true;
    }

    public CategoriaModel actualizarCategoria(Long id, CategoriaModel request) {
        Optional<CategoriaModel> encontrado = categoriaRepository.findById(id);
        if (encontrado.isEmpty())
            return null;
        CategoriaModel categoria = encontrado.get();
        if (request.getNombre() != null)
            categoria.setNombre(request.getNombre());
        if (request.getDescripcion() != null)
            categoria.setDescripcion(request.getDescripcion());
        if (request.getCategoriaPadreId() != null)
            categoria.setCategoriaPadreId(request.getCategoriaPadreId());
        return categoriaRepository.save(categoria);
    }
}
