package org.example.service.interfaces;

import java.util.List;

/**
 * Contrato genérico para todos los servicios.
 *
 * @param <T> Tipo de entidad.
 * @param <ID> Tipo del identificador.
 */
public interface BaseService<T, ID> {

    List<T> listar();

    T obtener(ID id);

    T guardar(T entity);

    void eliminar(ID id);
}