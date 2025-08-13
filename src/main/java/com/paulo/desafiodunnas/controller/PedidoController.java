package com.paulo.desafiodunnas.controller;

import com.paulo.desafiodunnas.model.*;
import com.paulo.desafiodunnas.repository.ClienteRepository;
import com.paulo.desafiodunnas.repository.FornecedorRepository;
import com.paulo.desafiodunnas.repository.ProdutoRepository;
import com.paulo.desafiodunnas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cliente/pedidos")
public class PedidoController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/novo")
    public String listarFornecedores(Model model) {
        model.addAttribute("fornecedores", fornecedorRepository.findAll());
        return "pedido/listar-fornecedores";
    }

    @GetMapping("/fornecedor/{fornecedorId}")
    public String exibirFormularioPedido(@PathVariable Long fornecedorId, Model model) {
        fornecedorRepository.findById(fornecedorId).ifPresent(fornecedor -> {
            model.addAttribute("fornecedor", fornecedor);
            model.addAttribute("produtos", produtoRepository.findByFornecedorId(fornecedorId));
        });
        return "pedido/cadastro-pedido";
    }

    @PostMapping("/criar")
    public String criarPedido(@RequestParam Long fornecedorId,
                              @RequestParam Map<String, String> quantidades,
                              @AuthenticationPrincipal Usuario usuario,
                              RedirectAttributes redirectAttributes) {

        Cliente cliente = clienteRepository.findByUsuario_Id(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        pedidoService.criarPedido(cliente, fornecedorId, quantidades);

        redirectAttributes.addFlashAttribute("sucesso", "Pedido criado com sucesso!");
        return "redirect:/cliente/home";
    }

    @PostMapping("/cancelar/{pedidoId}")
    public String cancelarPedido(@PathVariable Long pedidoId,
                                 @AuthenticationPrincipal Usuario usuario,
                                 RedirectAttributes redirectAttributes) {
        try {
            Cliente cliente = clienteRepository.findByUsuario_Id(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

            pedidoService.cancelarPedido(pedidoId, cliente);
            redirectAttributes.addFlashAttribute("sucesso", "Pedido #" + pedidoId + " cancelado com sucesso!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao cancelar pedido: " + e.getMessage());
        }

        return "redirect:/cliente/home";
    }

    @PostMapping("/pagar/{pedidoId}")
    public String pagarPedido(@PathVariable Long pedidoId,
                              @AuthenticationPrincipal Usuario usuario,
                              RedirectAttributes redirectAttributes) {
        try {
            Cliente cliente = clienteRepository.findByUsuario_Id(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

            pedidoService.pagarPedido(pedidoId, cliente);
            redirectAttributes.addFlashAttribute("sucesso", "Pedido #" + pedidoId + " pago com sucesso!");

        } catch (DataAccessException e) {
            String userFriendlyMessage = "Erro ao pagar pedido.";
            Throwable rootCause = e.getRootCause();
            if (rootCause != null && rootCause.getMessage() != null) {
                String fullMessage = rootCause.getMessage();
                int ondeIndex = fullMessage.indexOf("Onde:");
                if (ondeIndex != -1) {
                    userFriendlyMessage = fullMessage.substring(0, ondeIndex).trim();
                } else {
                    userFriendlyMessage = fullMessage;
                }
            }
            redirectAttributes.addFlashAttribute("erro", userFriendlyMessage);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro inesperado: " + e.getMessage());
        }

        return "redirect:/cliente/home";
    }
}