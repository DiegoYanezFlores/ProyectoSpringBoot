package org.example.controller;

import org.example.entity.Pedido;
import org.example.service.interfaces.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que gestiona los endpoints relacionados con la administración de pedidos.
 * Proporciona operaciones para listar todos los registros y crear nuevos pedidos en el sistema.
 * * @since 1.0
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /**
     * Recupera una lista completa con todos los pedidos registrados en el sistema.
     *  @return Una lista de objetos {@link Pedido}. Si no hay pedidos, la lista estará vacía.
     */
    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidoService.listarPedidos();
    }

    /**
     * Registra un nuevo pedido en el sistema.
     * <p>
     * Este método procesa la solicitud de creación y captura internamente posibles errores
     * de lógica de negocio (como falta de stock o clientes que no existen), devolviendo una
     * respuesta controlada al cliente.
     * </p>
     * * @param pedido El objeto {@link Pedido} enviado en el cuerpo de la petición HTTP (JSON).
     * @return Un {@link ResponseEntity} que contiene:
     * <ul>
     * <li>El objeto {@link Pedido} guardado y el estado HTTP 201 (CREATED) si la operación fue exitosa.</li>
     * <li>Un JSON con el mensaje de error y el estado HTTP 400 (BAD REQUEST) si falla la validación de negocio.</li>
     * </ul>
     */
    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = pedidoService.registrarPedido(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
        } catch (IllegalArgumentException e) {
            // Maneja limpiamente los errores de stock o de clientes inexistentes
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}