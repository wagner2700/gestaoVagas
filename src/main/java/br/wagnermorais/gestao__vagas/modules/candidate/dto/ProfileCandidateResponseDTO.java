package br.wagnermorais.gestao__vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    @Schema(example = "Desenvolvedor java")
    private String description;
    @Schema(example = "Joao")
    private String username;
    @Schema(example = "joao@gmail.com")
    private String email;
    private UUID id;
    @Schema(example = "Joao deSouza")
    private String name;
    
}
