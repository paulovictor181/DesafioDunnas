package com.paulo.desafiodunnas.repository;

import com.paulo.desafiodunnas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.paulo.desafiodunnas.enums.TipoUsuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsuario(String usuario);
    boolean existsByTipo(TipoUsuario tipo);
}
