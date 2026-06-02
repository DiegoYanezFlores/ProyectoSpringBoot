package org.example.service.interfaces;

import org.example.entity.Pedido;
import java.util.List;

public interface PedidoService {
    Pedido registrarPedido(Pedido pedido);
    List<Pedido> listarPedidos();
}