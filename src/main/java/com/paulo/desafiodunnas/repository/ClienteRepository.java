package com.paulo.desafiodunnas.repository;

import com.paulo.desafiodunnas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUsuario_Id(Long usuarioId);

    @Query(value = "SELECT adicionar_saldo(:clienteId, :valor)", nativeQuery = true)
    void adicionarSaldo(@Param("clienteId") Long clienteId, @Param("valor") BigDecimal valor);

    @Query(value = "SELECT registrar_pagamento(:clienteId, :valor, :metodoPagamento)", nativeQuery = true)
    void registrarPagamento(@Param("clienteId") Long clienteId, @Param("valor") BigDecimal valor, @Param("metodoPagamento") String metodoPagamento);
}
