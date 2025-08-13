package com.paulo.desafiodunnas.service;

import com.paulo.desafiodunnas.model.Cliente;
import com.paulo.desafiodunnas.model.Pagamento;
import com.paulo.desafiodunnas.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> findByClienteAndFiltros(Cliente cliente, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return pagamentoRepository.findByClienteAndDataPagamentoBetween(cliente, dataInicio, dataFim);
    }
}
