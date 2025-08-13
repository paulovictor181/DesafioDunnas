package com.paulo.desafiodunnas.repository;

import com.paulo.desafiodunnas.model.Cliente;
import com.paulo.desafiodunnas.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    @Query("SELECT p FROM Pagamento p WHERE p.cliente = :cliente AND " +
            "(p.dataPagamento >= COALESCE(:dataInicio, p.dataPagamento)) AND " +
            "(p.dataPagamento <= COALESCE(:dataFim, p.dataPagamento)) " +
            "ORDER BY p.dataPagamento DESC")
    List<Pagamento> findByClienteAndDataPagamentoBetween(@Param("cliente") Cliente cliente,
                                                         @Param("dataInicio") LocalDateTime dataInicio,
                                                         @Param("dataFim") LocalDateTime dataFim);


    List<Pagamento> findByCliente(Cliente cliente);
}
