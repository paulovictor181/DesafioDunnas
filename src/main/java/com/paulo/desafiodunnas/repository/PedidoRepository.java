package com.paulo.desafiodunnas.repository;

import com.paulo.desafiodunnas.enums.StatusPedido;
import com.paulo.desafiodunnas.model.Cliente;
import com.paulo.desafiodunnas.model.Fornecedor;
import com.paulo.desafiodunnas.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteAndStatusOrderByDataPedidoDesc(Cliente cliente, StatusPedido status);

    List<Pedido> findByClienteOrderByDataPedidoDesc(Cliente cliente);

    List<Pedido> findByFornecedorAndStatusOrderByDataPedidoDesc(Fornecedor fornecedor, StatusPedido status);

    @Query("SELECT p FROM Pedido p WHERE p.cliente = :cliente AND " +
            "(p.dataPedido >= COALESCE(:dataInicio, p.dataPedido)) AND " +
            "(p.dataPedido <= COALESCE(:dataFim, p.dataPedido)) " +
            "ORDER BY p.dataPedido DESC")
    List<Pedido> findByClienteAndDataPedidoBetween(@Param("cliente") Cliente cliente,
                                                   @Param("dataInicio") LocalDateTime dataInicio,
                                                   @Param("dataFim") LocalDateTime dataFim);

    @Query("SELECT p FROM Pedido p WHERE p.fornecedor = :fornecedor AND p.status = :status AND " +
            "(p.dataPedido >= COALESCE(:dataInicio, p.dataPedido)) AND " +
            "(p.dataPedido <= COALESCE(:dataFim, p.dataPedido)) " +
            "ORDER BY p.dataPedido DESC")
    List<Pedido> findByFornecedorAndStatusAndDataPedidoBetween(@Param("fornecedor") Fornecedor fornecedor,
                                                               @Param("status") StatusPedido status,
                                                               @Param("dataInicio") LocalDateTime dataInicio,
                                                               @Param("dataFim") LocalDateTime dataFim);

    @Query(value = "SELECT criar_pedido(:clienteId, :fornecedorId, :cupomId, :produtoIds, :quantidades)", nativeQuery = true)
    Long criarPedido(
            @Param("clienteId") Long clienteId,
            @Param("fornecedorId") Long fornecedorId,
            @Param("cupomId") Long cupomId,
            @Param("produtoIds") Long[] produtoIds,
            @Param("quantidades") Integer[] quantidades
    );

    @Query(value = "SELECT cancelar_pedido(:pedidoId, :clienteId)", nativeQuery = true)
    void cancelarPedido(
            @Param("pedidoId") Long pedidoId,
            @Param("clienteId") Long clienteId
    );

    @Query(value = "SELECT pagar_pedido(:pedidoId, :clienteId)", nativeQuery = true)
    void pagarPedido(@Param("pedidoId") Long pedidoId, @Param("clienteId") Long clienteId);
}
