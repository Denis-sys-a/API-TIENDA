package com.sodimac.api.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sodimac.api.models.CategoriaModel;
import com.sodimac.api.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public ArrayList<CategoriaModel> obtenerCategorias() {
        return (ArrayList<CategoriaModel>) categoriaRepository.findAll();
    }


    public CategoriaModel guardarCategoria(CategoriaModel categoria) {
        return categoriaRepository.save(categoria);
    }


    public Optional<CategoriaModel> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }


    public boolean eliminarCategoria(Long id) {
        try {
            categoriaRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
