package com.paulo.desafiodunnas.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String redirecionarParaHome(Authentication authentication) {
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("ROLE_CLIENTE")) {
                    return "redirect:/cliente/home";
                } else if (grantedAuthority.getAuthority().equals("ROLE_FORNECEDOR")) {
                    return "redirect:/fornecedor/home";
                } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                    return "redirect:/admin/home";
                }
            }
        }
        return "redirect:/login";
    }
}
