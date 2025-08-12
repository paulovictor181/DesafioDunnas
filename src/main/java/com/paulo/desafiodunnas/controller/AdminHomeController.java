package com.paulo.desafiodunnas.controller;

import com.paulo.desafiodunnas.model.Usuario;
import com.paulo.desafiodunnas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminHomeController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/admin/home")
    public String homeAdmin(Model model, @RequestParam(required = false) String filtro) {
        List<Usuario> usuarios;
        if (filtro != null && !filtro.isEmpty()) {
            usuarios = usuarioService.listarTodosUsuarios(filtro);
        } else {
            usuarios = usuarioService.listarTodosUsuarios();
        }
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("filtro", filtro);
        return "home-admin";
    }
}
