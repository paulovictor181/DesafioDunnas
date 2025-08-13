package com.paulo.desafiodunnas.repository;

import com.paulo.desafiodunnas.model.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {
}
