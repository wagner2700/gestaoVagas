package br.wagnermorais.gestao__vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity // Habilitar verificaçãop de regras e permissãopor role
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;

    // Liberar rotas
    private static final String[] SWAGGER_LIST = {
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "swagger-resources/**"
    };

    @Bean // Sobrescrever implementação padrão de segurança
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
            // permisão para essa duas rotas
            auth.requestMatchers("/candidate/").permitAll()
            .requestMatchers("/company/").permitAll()
            .requestMatchers("/company/auth").permitAll()
            .requestMatchers("/candidate/auth").permitAll()
            .requestMatchers(SWAGGER_LIST).permitAll();
            // demais rotas, exigir autenticação
            auth.anyRequest().authenticated();
        })
        .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
        .addFilterBefore(securityFilter , BasicAuthenticationFilter.class)
        
            
        
        ;
        return httpSecurity.build();
    }

    @Bean // criptografar senha
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
