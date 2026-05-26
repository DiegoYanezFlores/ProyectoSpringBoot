package org.example.service;

import org.example.entity.Producto;
import org.example.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // 1. Registrar en Spring
public class ProductoServiceImpl implements ProductoService { // 2. Cumplir el contrato

    @Autowired // 3. Inyección de Dependencias
    private ProductoRepository repository;

    @Override // 4. Implementar método Listar
    public List<Producto> listarProductos() {
        return repository.findAll(); // Llama al repositorio para traer todo de la BD
    }

    @Override // 5. Implementar método Guardar con Lógica de Negocio
    public Producto guardarProducto(Producto producto) {
        // REGLA DE NEGOCIO 1: El precio no puede ser menor a cero
        if (producto.getPrecio() == null || producto.getPrecio() < 0.0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        // REGLA DE NEGOCIO 2: El stock no puede ser menor a cero
        if (producto.getStock() == null || producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock de inventario no puede ser negativo");
        }
        // Si pasa las validaciones, se guarda de forma segura en PostgreSQL
        return repository.save(producto);
    }
}