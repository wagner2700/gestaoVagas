package br.com.wagnermorais.fron_gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/candidate/login").permitAll()
            .requestMatchers("/candidate/create").permitAll()
            .requestMatchers("/company/create").permitAll()
            .requestMatchers("/company/login").permitAll()
            .requestMatchers("/company/singIn").permitAll()
            .requestMatchers("/candidate/singIn").permitAll();
            auth.anyRequest().authenticated();
        })
        .formLogin( form -> form.loginPage("/candidate/login"));
        return http.build();
    }
    
}
