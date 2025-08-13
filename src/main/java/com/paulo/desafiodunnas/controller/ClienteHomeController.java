package com.paulo.desafiodunnas.controller;

import com.paulo.desafiodunnas.model.Cliente;
import com.paulo.desafiodunnas.model.Pedido;
import com.paulo.desafiodunnas.model.Usuario;
import com.paulo.desafiodunnas.repository.ClienteRepository;
import com.paulo.desafiodunnas.service.ClienteService;
import com.paulo.desafiodunnas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Controller
public class ClienteHomeController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/cliente/home")
    public String homeCliente(Model model, @AuthenticationPrincipal Usuario usuario) {
        if (usuario != null) {
            clienteRepository.findByUsuario_Id(usuario.getId()).ifPresent(cliente -> {
                model.addAttribute("cliente", cliente);

                List<Pedido> pedidos = pedidoService.findPendentesByCliente(cliente);
                model.addAttribute("pedidos", pedidos);
            });
        }

        if (!model.containsAttribute("pedidos")) {
            model.addAttribute("pedidos", Collections.emptyList());
        }

        return "home-cliente";
    }

    @PostMapping("/cliente/saldo/adicionar")
    public String adicionarSaldo(@RequestParam BigDecimal valor,
                                 @RequestParam String metodoPagamento,
                                 @AuthenticationPrincipal Usuario usuario,
                                 RedirectAttributes redirectAttributes) {
        try {
            Cliente cliente = clienteRepository.findByUsuario_Id(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

            clienteService.registrarPagamento(cliente.getId(), valor, metodoPagamento);
            redirectAttributes.addFlashAttribute("sucesso", "Saldo adicionado com sucesso!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao adicionar saldo: " + e.getMessage());
        }
        return "redirect:/cliente/home";
    }
}
