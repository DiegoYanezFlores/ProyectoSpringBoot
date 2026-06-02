package org.example.service.interfaces;

import org.example.entity.Categoria;

import java.util.List;

public interface CategoriaService
        extends BaseService<Categoria, Long> {

    List<Categoria> listar();

    Categoria obtener(Long id);

    Categoria guardar(Categoria categoria);

    void eliminar(Long id);

}