package com.paulo.desafiodunnas.service;

import com.paulo.desafiodunnas.enums.TipoUsuario;
import com.paulo.desafiodunnas.model.Cliente;
import com.paulo.desafiodunnas.model.Usuario;
import com.paulo.desafiodunnas.model.Fornecedor;
import com.paulo.desafiodunnas.repository.ClienteRepository;
import com.paulo.desafiodunnas.repository.FornecedorRepository;
import com.paulo.desafiodunnas.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void cadastrarUsuario(String nomeUsuario, String senha, TipoUsuario tipo, String nome, String cpf,
                                     LocalDate dataNascimento, String nomeFornecedor, String cnpj) {

        if (usuarioRepository.findByUsuario(nomeUsuario) != null) {
            throw new IllegalStateException("Nome de usuário já existe: " + nomeUsuario);
        }
        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsuario(nomeUsuario);
        novoUsuario.setSenha(passwordEncoder.encode(senha));
        novoUsuario.setTipo(tipo);
        usuarioRepository.save(novoUsuario);

        if (tipo == TipoUsuario.CLIENTE) {
            Cliente novoCliente = new Cliente();
            novoCliente.setNome(nome);
            novoCliente.setCpf(cpf);
            novoCliente.setDataNascimento(dataNascimento);
            novoCliente.setUsuario(novoUsuario);
            clienteRepository.save(novoCliente);
        } else if (tipo == TipoUsuario.FORNECEDOR) {
            Fornecedor novoFornecedor = new Fornecedor();
            novoFornecedor.setNome(nomeFornecedor);
            novoFornecedor.setCnpj(cnpj);
            novoFornecedor.setUsuario(novoUsuario);
            fornecedorRepository.save(novoFornecedor);
        }
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> listarTodosUsuarios(String filtro) {
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getUsuario().toLowerCase().contains(filtro.toLowerCase()))
                .collect(Collectors.toList());
    }
}
