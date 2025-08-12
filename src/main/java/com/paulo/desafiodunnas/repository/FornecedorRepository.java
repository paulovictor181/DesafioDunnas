package com.paulo.desafiodunnas.repository;

import com.paulo.desafiodunnas.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Optional<Fornecedor> findByUsuario_Id(Long usuarioId);
}