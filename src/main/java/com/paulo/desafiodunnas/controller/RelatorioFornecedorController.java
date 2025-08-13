package com.paulo.desafiodunnas.controller;

import com.paulo.desafiodunnas.model.Fornecedor;
import com.paulo.desafiodunnas.model.Pedido;
import com.paulo.desafiodunnas.model.Usuario;
import com.paulo.desafiodunnas.repository.FornecedorRepository;
import com.paulo.desafiodunnas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/fornecedor/relatorios")
public class RelatorioFornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/vendas")
    public String relatorioVendas(
            Model model, @AuthenticationPrincipal Usuario usuario,
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim) {

        if (usuario != null) {
            Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findByUsuario_Id(usuario.getId());
            if (fornecedorOptional.isPresent()) {
                Fornecedor fornecedor = fornecedorOptional.get();
                List<Pedido> vendas;

                LocalDateTime inicio = (dataInicio != null) ? dataInicio.atStartOfDay() : null;
                LocalDateTime fim = (dataFim != null) ? dataFim.atTime(23, 59, 59) : null;

                if (dataInicio != null || dataFim != null) {
                    vendas = pedidoService.findConcluidosByFornecedorAndDataBetween(fornecedor, inicio, fim);
                } else {
                    vendas = pedidoService.findConcluidosByFornecedor(fornecedor);
                }

                model.addAttribute("vendas", vendas);
            }
        }

        if (!model.containsAttribute("vendas")) {
            model.addAttribute("vendas", Collections.emptyList());
        }

        return "relatorios/vendas";
    }
}
