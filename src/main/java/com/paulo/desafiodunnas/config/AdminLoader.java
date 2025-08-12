package com.paulo.desafiodunnas.config;

import com.paulo.desafiodunnas.enums.TipoUsuario;
import com.paulo.desafiodunnas.model.Usuario;
import com.paulo.desafiodunnas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;


@Component
public class AdminLoader implements CommandLineRunner{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!usuarioRepository.existsByTipo(TipoUsuario.ADMIN)) {
            Usuario admin = new Usuario();
            admin.setUsuario("admin");
            admin.setSenha(passwordEncoder.encode("admin"));
            admin.setTipo(TipoUsuario.ADMIN);

            usuarioRepository.save(admin);
            System.out.println("Usuário ADMIN padrão criado com sucesso.");
        }
    }
}
