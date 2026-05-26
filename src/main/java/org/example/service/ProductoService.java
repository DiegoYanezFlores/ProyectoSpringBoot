package org.example.service;

import org.example.entity.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> listarProductos();            // Contrato: "El sistema debe poder listar productos"
    Producto guardarProducto(Producto producto); // Contrato: "El sistema debe poder guardar un producto"
}