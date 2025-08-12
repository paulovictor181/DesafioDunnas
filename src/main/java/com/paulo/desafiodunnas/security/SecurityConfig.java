package com.paulo.desafiodunnas.security;

import com.paulo.desafiodunnas.config.LoginSuccessHandler;
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
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login", "/registrar", "/css/**","/webjars/**","/WEB-INF/jsp/**","/error/**").permitAll() // Rotas públicas
                        .requestMatchers("/cliente/**").hasRole("CLIENTE") // Rotas de cliente
                        .requestMatchers("/fornecedor/**").hasRole("FORNECEDOR") // Rotas de fornecedor
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Rotas de admin
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(new LoginSuccessHandler())
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.sendRedirect(request.getContextPath() + "/error?status=403");
                        })
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Define o codificador de senhas
        return new BCryptPasswordEncoder();
    }
}