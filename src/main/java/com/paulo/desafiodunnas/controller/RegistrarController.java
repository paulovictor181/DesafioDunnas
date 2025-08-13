package com.paulo.desafiodunnas.controller;

import com.paulo.desafiodunnas.enums.TipoUsuario;
import com.paulo.desafiodunnas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RegistrarController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registrar")
    public String exibirFormularioRegistro(Model model) {
        // Envia para a view a lista de Tipos de Usuário, exceto ADMIN
        Set<TipoUsuario> tipos = EnumSet.allOf(TipoUsuario.class).stream()
                .filter(tipo -> tipo != TipoUsuario.ADMIN)
                .collect(Collectors.toSet());
        model.addAttribute("tipos", tipos);
        return "registrar";
    }

    @PostMapping("/registrar")
    public String processarRegistro(@RequestParam String usuario,
                                    @RequestParam String senha,
                                    @RequestParam TipoUsuario tipo,
                                    @RequestParam(required = false) String nome,
                                    @RequestParam(required = false) String cpf,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataNascimento,
                                    @RequestParam(required = false) String nomeFornecedor,
                                    @RequestParam(required = false) String cnpj,
                                    RedirectAttributes redirectAttributes) {
        try {
            // Passa todos os dados para o serviço
            usuarioService.cadastrarUsuario(usuario, senha, tipo, nome, cpf, dataNascimento, nomeFornecedor, cnpj);
            redirectAttributes.addFlashAttribute("mensagem", "Usuário registrado com sucesso! Faça o login.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Falha em registrar o usuário");
            return "redirect:/registrar";
        }
    }
}
