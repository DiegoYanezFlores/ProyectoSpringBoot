package org.example.service;

import org.example.entity.Pedido;
import java.util.List;

public interface PedidoService {
    Pedido registrarPedido(Pedido pedido);
    List<Pedido> listarPedidos();
}