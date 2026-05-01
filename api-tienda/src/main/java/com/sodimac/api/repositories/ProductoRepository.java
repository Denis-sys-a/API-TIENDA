package com.sodimac.api.repositories;

import com.sodimac.api.models.ProductoModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Long> {

    List<ProductoModel> findByCategoria_Id(Long categoriaId);

    List<ProductoModel> findByMarcaIgnoreCase(String marca);

    List<ProductoModel> findByNombreContainingIgnoreCase(String texto);

    List<ProductoModel> findByPrecioTarjetaCmrLessThan(Double precio);

}
