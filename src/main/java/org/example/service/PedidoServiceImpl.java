package org.example.service;

import org.example.entity.DetallePedido;
import org.example.entity.Pedido;
import org.example.entity.Producto;
import org.example.repository.ClienteRepository;
import org.example.repository.PedidoRepository;
import org.example.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private ProductoRepository productoRepository;
    @Autowired private ClienteRepository clienteRepository;

    @Override
    @Transactional // CRUCIAL: Si algo falla aquí adentro, se deshace todo en la base de datos (Rollback)
    public Pedido registrarPedido(Pedido pedido) {

        // 1. Validar que el cliente exista
        if (pedido.getCliente() == null || pedido.getCliente().getId() == null) {
            throw new IllegalArgumentException("Debe asignar un cliente válido al pedido.");
        }
        clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("El cliente especificado no existe."));

        // 2. Procesar los detalles de la compra
        double totalPedido = 0.0;

        if (pedido.getDetalles() == null || pedido.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("El pedido debe contener al menos un producto.");
        }

        for (DetallePedido detalle : pedido.getDetalles()) {
            // Validar existencia del producto solicitado
            Producto prodBD = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new IllegalArgumentException("El producto con ID " + detalle.getProducto().getId() + " no existe."));

            // Validar Stock disponible
            if (prodBD.getStock() < detalle.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para: " + prodBD.getNombre() + ". Disponible: " + prodBD.getStock());
            }

            // Restar stock físico del inventario
            prodBD.setStock(prodBD.getStock() - detalle.getCantidad());
            productoRepository.save(prodBD); // Actualiza el stock en PostgreSQL

            // Calcular precios del detalle de forma interna (evita hackeos desde el frontend)
            detalle.setPrecioUnitario(prodBD.getPrecio());
            double subtotal = prodBD.getPrecio() * detalle.getCantidad();
            detalle.setSubtotal(subtotal);
            detalle.setPedido(pedido); // Vincula el detalle a esta cabecera

            totalPedido += subtotal;
        }

        // 3. Asignar el total calculado final al pedido
        pedido.setTotal(totalPedido);
        pedido.setEstado("PROCESADO");

        // 4. Guardar el pedido completo (Por Cascada guardará también los renglones del detalle)
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}