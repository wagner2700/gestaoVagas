package br.wagnermorais.gestao__vagas.modules.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.wagnermorais.gestao__vagas.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository< JobEntity  ,UUID >{
    
}
