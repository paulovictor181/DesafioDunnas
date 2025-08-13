package com.paulo.desafiodunnas.repository;

import com.paulo.desafiodunnas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByFornecedorId(Long fornecedorId);

    @Query(value = "SELECT * FROM produtos p WHERE p.fornecedor_id = :fornecedorId AND p.status = 'ATIVO' AND " +
            "(:nome IS NULL OR p.nome ILIKE CONCAT('%', :nome, '%'))",
            nativeQuery = true)
    List<Produto> findByFornecedorIdAndNome(Long fornecedorId, String nome);
}
