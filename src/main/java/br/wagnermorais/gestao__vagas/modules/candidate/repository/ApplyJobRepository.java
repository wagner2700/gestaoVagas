package br.wagnermorais.gestao__vagas.modules.candidate.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.wagnermorais.gestao__vagas.modules.candidate.entity.ApplyJobEntity;


public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity , UUID>{
    
}
