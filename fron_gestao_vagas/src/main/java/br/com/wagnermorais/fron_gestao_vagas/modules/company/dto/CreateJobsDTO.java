package br.com.wagnermorais.fron_gestao_vagas.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobsDTO {
    
    private String description;
    private String benefits;
    private String level;
}
