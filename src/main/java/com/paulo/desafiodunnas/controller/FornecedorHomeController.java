package com.paulo.desafiodunnas.controller;

import com.paulo.desafiodunnas.model.Fornecedor;
import com.paulo.desafiodunnas.model.Produto;
import com.paulo.desafiodunnas.model.Usuario;
import com.paulo.desafiodunnas.repository.FornecedorRepository;
import com.paulo.desafiodunnas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;


@Controller
public class FornecedorHomeController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FornecedorRepository fornecedorRepository;


    @GetMapping("/fornecedor/home")
    public String homeFornecedor(Model model, @AuthenticationPrincipal Usuario usuario, @RequestParam(required = false) String filtro) {
        if (usuario != null) {
            fornecedorRepository.findByUsuario_Id(usuario.getId()).ifPresent(fornecedor -> {
                model.addAttribute("produtos", produtoService.buscarPorFornecedorComFiltro(fornecedor, filtro));
            });
        }

        if (!model.containsAttribute("produtos")) {
            model.addAttribute("produtos", Collections.emptyList());
        }

        model.addAttribute("novoProduto", new Produto());
        model.addAttribute("filtro", filtro);
        return "home-fornecedor";
    }

    @PostMapping("/fornecedor/produtos/add")
    public String addProduto(Produto novoProduto, @AuthenticationPrincipal Usuario usuario, // Injete Usuario diretamente
                             RedirectAttributes redirectAttributes) {

        if (usuario != null) {
            fornecedorRepository.findByUsuario_Id(usuario.getId()).ifPresentOrElse(fornecedor -> {
                novoProduto.setFornecedor(fornecedor);
                produtoService.cadastrarProduto(novoProduto);
                redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
            }, () -> {
                redirectAttributes.addFlashAttribute("erro", "Fornecedor não encontrado.");
            });
        } else {
            redirectAttributes.addFlashAttribute("erro", "Usuário não autenticado.");
        }

        return "redirect:/fornecedor/home";
    }
}
