package br.wagnermorais.gestao__vagas.modules.company.UseCases;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCompanyResponseDTO {
    
    private String acess_token;
    private Long expires_in;
    private List<String> roles;
}
