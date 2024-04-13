package br.wagnermorais.gestao__vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity , UUID>{
    // procurar e se não  entrar retornar algumas opções
    Optional<CandidateEntity> findByUsernameOrEmail(String username , String email);
    
}
