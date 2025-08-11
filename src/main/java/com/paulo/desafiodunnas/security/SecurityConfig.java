package com.paulo.desafiodunnas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login", "/registrar", "/css/**","/WEB-INF/jsp/**").permitAll() // Rotas públicas
                        .requestMatchers("/pedidos/**").hasRole("CLIENTE") // Rotas de cliente
                        .requestMatchers("/meus-produtos/**").hasRole("FORNECEDOR") // Rotas de fornecedor
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.loginPage("/login").permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails cliente = User.builder()
                .username("cliente1")
                .password(passwordEncoder().encode("senha123"))
                .roles("CLIENTE")
                .build();

        UserDetails fornecedor = User.builder()
                .username("fornecedor1")
                .password(passwordEncoder().encode("senha123"))
                .roles("FORNECEDOR")
                .build();

        return new InMemoryUserDetailsManager(cliente, fornecedor);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Define o codificador de senhas
        return new BCryptPasswordEncoder();
    }
}