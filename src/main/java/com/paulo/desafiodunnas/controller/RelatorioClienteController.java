package com.paulo.desafiodunnas.controller;

import com.paulo.desafiodunnas.model.Cliente;
import com.paulo.desafiodunnas.model.Usuario;
import com.paulo.desafiodunnas.repository.ClienteRepository;
import com.paulo.desafiodunnas.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/cliente/relatorios")
public class RelatorioClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/creditos")
    public String relatorioCreditos(Model model, @AuthenticationPrincipal Usuario usuario,
                                    @RequestParam(required = false) LocalDate dataInicio,
                                    @RequestParam(required = false) LocalDate dataFim) {
        if (usuario != null) {
            clienteRepository.findByUsuario_Id(usuario.getId()).ifPresent(cliente -> {
                LocalDateTime inicio = (dataInicio != null) ? dataInicio.atStartOfDay() : null;
                LocalDateTime fim = (dataFim != null) ? dataFim.atTime(23, 59, 59) : null;

                model.addAttribute("cliente", cliente);
                model.addAttribute("pagamentos", pagamentoService.findByClienteAndFiltros(cliente, inicio, fim));
            });
        }
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        return "relatorios/creditos";
    }
}