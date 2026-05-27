package org.example.repository;
import org.example.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface PedidoRepository extends JpaRepository<Pedido, Long> {}