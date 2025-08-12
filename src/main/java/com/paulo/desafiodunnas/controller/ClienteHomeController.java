package com.paulo.desafiodunnas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteHomeController {

    // @Autowired
    // private PedidoService pedidoService;

    @GetMapping("/cliente/home")
    public String homeCliente(Model model, @RequestParam(required = false) String filtro) {
        // List<Pedido> pedidos = pedidoService.findPendentesByCliente(getClienteLogado(), filtro);
        // model.addAttribute("pedidos", pedidos);
        model.addAttribute("filtro", filtro);
        return "home-cliente";
    }
}
