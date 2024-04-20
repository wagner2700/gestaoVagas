package br.wagnermorais.gestao__vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Schema(example = "Daniel de Souza")
    private String name;
    
    @NotBlank
    @Pattern(regexp = "\\S+" , message = "Ocampo [username] não deve conter espaços")
    @Schema(example = "Daniel")
    private String username;

    @Email(message = "O campo [email] deve conter um email válido")
    @Schema(example = "daniel@gmail.com")
    private String email;

    @Length(min = 10 , max = 100)
    @Schema(example = "admin@1234" , minLength = 10 , maxLength = 100 )
    private String password;

    @Schema(example = "Desenvolvedor java")
    private String description;
    private String curriculum;
     @CreationTimestamp
    private LocalDateTime createAt;
}
