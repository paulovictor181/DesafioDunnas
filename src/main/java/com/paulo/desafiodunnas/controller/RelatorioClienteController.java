package com.paulo.desafiodunnas.controller;

import com.paulo.desafiodunnas.model.Cliente;
import com.paulo.desafiodunnas.model.Pedido;
import com.paulo.desafiodunnas.model.Usuario;
import com.paulo.desafiodunnas.repository.ClienteRepository;
import com.paulo.desafiodunnas.service.PagamentoService;
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
@RequestMapping("/cliente/relatorios")
public class RelatorioClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/creditos")
    public String relatorioCreditos(Model model, @AuthenticationPrincipal Usuario usuario,
                                    @RequestParam(required = false) LocalDate dataInicio,
                                    @RequestParam(required = false) LocalDate dataFim) {
        if (usuario != null) {
            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario_Id(usuario.getId());
            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();

                LocalDateTime inicio = (dataInicio != null) ? dataInicio.atStartOfDay() : null;
                LocalDateTime fim = (dataFim != null) ? dataFim.atTime(23, 59, 59) : null;

                model.addAttribute("cliente", cliente);
                model.addAttribute("pagamentos", pagamentoService.findByClienteAndFiltros(cliente, inicio, fim));
            }
        }
        if (!model.containsAttribute("pagamentos")) {
            model.addAttribute("pagamentos", Collections.emptyList());
        }

        return "relatorios/creditos";
    }

    @GetMapping("/compras")
    public String relatorioCompras(
            Model model, @AuthenticationPrincipal Usuario usuario,
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim) {

        if (usuario != null) {
            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario_Id(usuario.getId());
            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                List<Pedido> pedidos;

                LocalDateTime inicio = (dataInicio != null) ? dataInicio.atStartOfDay() : null;
                LocalDateTime fim = (dataFim != null) ? dataFim.atTime(23, 59, 59) : null;

                if (dataInicio != null || dataFim != null) {
                    pedidos = pedidoService.findPedidosByClienteAndDataBetween(cliente, inicio, fim);
                } else {
                    pedidos = pedidoService.findPedidosByCliente(cliente);
                }

                model.addAttribute("pedidos", pedidos);
            }
        }

        if (!model.containsAttribute("pedidos")) {
            model.addAttribute("pedidos", Collections.emptyList());
        }

        return "relatorios/compras";
    }
}