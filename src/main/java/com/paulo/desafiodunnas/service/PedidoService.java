package com.paulo.desafiodunnas.service;

import com.paulo.desafiodunnas.enums.StatusPedido;
import com.paulo.desafiodunnas.model.Cliente;
import com.paulo.desafiodunnas.model.Fornecedor;
import com.paulo.desafiodunnas.model.Pedido;
import com.paulo.desafiodunnas.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findPedidosByCliente(Cliente cliente) {
        return pedidoRepository.findByClienteOrderByDataPedidoDesc(cliente);
    }

    public List<Pedido> findPendentesByCliente(Cliente cliente) {
        return pedidoRepository.findByClienteAndStatusOrderByDataPedidoDesc(cliente, StatusPedido.PENDENTE);
    }

    public List<Pedido> findPedidosByClienteAndDataBetween(Cliente cliente, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return pedidoRepository.findByClienteAndDataPedidoBetween(cliente, dataInicio, dataFim);
    }

    public List<Pedido> findConcluidosByFornecedor(Fornecedor fornecedor) {
        return pedidoRepository.findByFornecedorAndStatusOrderByDataPedidoDesc(fornecedor, StatusPedido.CONCLUIDO);
    }

    public List<Pedido> findConcluidosByFornecedorAndDataBetween(Fornecedor fornecedor, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return pedidoRepository.findByFornecedorAndStatusAndDataPedidoBetween(fornecedor, StatusPedido.CONCLUIDO, dataInicio, dataFim);
    }

    @Transactional
    public Long criarPedido(Cliente cliente, Long fornecedorId, Map<String, String> quantidadesMap) {
        List<Long> produtoIdsList = new ArrayList<>();
        List<Integer> quantidadesList = new ArrayList<>();

        for (Map.Entry<String, String> entry : quantidadesMap.entrySet()) {
            if (entry.getKey().startsWith("quantidades[")) {
                int quantidade = Integer.parseInt(entry.getValue());
                if (quantidade > 0) {
                    Long produtoId = Long.parseLong(entry.getKey().replaceAll("\\D+", ""));
                    produtoIdsList.add(produtoId);
                    quantidadesList.add(quantidade);
                }
            }
        }

        return pedidoRepository.criarPedido(
                cliente.getId(),
                fornecedorId,
                null,
                produtoIdsList.toArray(new Long[0]),
                quantidadesList.toArray(new Integer[0])
        );
    }

    @Transactional
    public void cancelarPedido(Long pedidoId, Cliente cliente) {
        pedidoRepository.cancelarPedido(pedidoId, cliente.getId());
    }

    @Transactional
    public void pagarPedido(Long pedidoId, Cliente cliente) {
        pedidoRepository.pagarPedido(pedidoId, cliente.getId());
    }
}
