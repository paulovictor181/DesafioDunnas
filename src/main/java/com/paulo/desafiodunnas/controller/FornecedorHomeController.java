package com.paulo.desafiodunnas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FornecedorHomeController {

    // @Autowired
    // private ProdutoService produtoService;

    @GetMapping("/fornecedor/home")
    public String homeFornecedor(Model model, @RequestParam(required = false) String filtro) {
        // List<Produto> produtos = produtoService.findByFornecedor(getFornecedorLogado(), filtro);
        // model.addAttribute("produtos", produtos);
        model.addAttribute("filtro", filtro);
        return "home-fornecedor";
    }
}
