package br.com.wagnermorais.fron_gestao_vagas.modules.candidate.controller.dto;

import java.util.List;

import lombok.Data;


@Data
public class Token {
    
    private String acess_token;
    private List<String> roles;
    private Long expires_in;

}
