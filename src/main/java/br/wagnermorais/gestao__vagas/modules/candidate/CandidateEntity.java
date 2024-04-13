package br.wagnermorais.gestao__vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
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
    private UUID ID;

    private String name;
    
    @NotBlank
    @Pattern(regexp = "\\S+" , message = "Ocampo [username] não deve conter espaços")
    private String username;
    @Email(message = "O campo [email] deve conter um email válido")
    private String email;
    @Length(min = 10 , max = 100)
    private String password;
    private String description;
    private String curriculum;
     @CreationTimestamp
    private LocalDateTime createAt;
}