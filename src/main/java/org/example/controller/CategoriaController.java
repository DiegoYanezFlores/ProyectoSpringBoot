package org.example.controller;

import org.example.entity.Categoria;
import org.example.service.interfaces.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador encargado de gestionar
 * las peticiones HTTP relacionadas
 * con las categorías.
 *
 * Responsabilidad:
 * Recibir solicitudes del cliente
 * y delegar la lógica de negocio
 * al servicio correspondiente.
 *
 * Arquitectura MVC:
 *
 * M -> Categoria
 * V -> Angular
 * C -> CategoriaController
 *
 * @author Diego Yánez
 * @version 1.0
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    /**
     * Servicio inyectado mediante
     * Inyección de Dependencias.
     *
     * El controlador depende de una
     * abstracción y no de una implementación.
     *
     * Principio SOLID:
     * Dependency Inversion Principle (D).
     */
    private final CategoriaService categoriaService;

    public CategoriaController(
            CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Obtiene todas las categorías
     * registradas en el sistema.
     *
     * @return lista de categorías.
     */
    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listar();
    }

    /**
     * Busca una categoría utilizando
     * su identificador.
     *
     * @param id identificador único
     *           de la categoría.
     *
     * @return categoría encontrada.
     */
    @GetMapping("/{id}")
    public Categoria obtener(
            @PathVariable Long id) {

        return categoriaService.obtener(id);
    }

    /**
     * Registra una nueva categoría.
     *
     * @param categoria información
     *                  de la categoría.
     *
     * @return categoría almacenada.
     */
    @PostMapping
    public Categoria guardar(
            @RequestBody Categoria categoria) {

        return categoriaService.guardar(categoria);
    }

    /**
     * Actualiza una categoría existente.
     *
     * @param id identificador de la categoría.
     * @param categoria nuevos datos.
     *
     * @return categoría actualizada.
     */
    @PutMapping("/{id}")
    public Categoria actualizar(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {

        Categoria existente =
                categoriaService.obtener(id);

        existente.setNombre(
                categoria.getNombre()
        );

        return categoriaService.guardar(existente);
    }

    /**
     * Elimina una categoría utilizando
     * su identificador.
     *
     * @param id identificador de la categoría.
     */
    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id) {

        categoriaService.eliminar(id);
    }
}