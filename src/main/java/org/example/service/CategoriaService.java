package org.example.service;

import org.example.entity.Categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> listar();

    Categoria obtener(Long id);

    Categoria guardar(Categoria categoria);

    void eliminar(Long id);
}