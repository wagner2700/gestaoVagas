package br.com.wagnermorais.fron_gestao_vagas.modules.candidate.controller.dto;

import java.sql.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {

    private UUID id;
    private String description;
    private String benefits;
    private String level;
    private UUID companyId;
    private Date createAt;
    
}
