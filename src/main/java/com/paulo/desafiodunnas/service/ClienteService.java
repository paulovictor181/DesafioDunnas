package com.paulo.desafiodunnas.service;

import com.paulo.desafiodunnas.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void registrarPagamento(Long clienteId, BigDecimal valor, String metodoPagamento) {
        clienteRepository.registrarPagamento(clienteId, valor, metodoPagamento);
    }
}
