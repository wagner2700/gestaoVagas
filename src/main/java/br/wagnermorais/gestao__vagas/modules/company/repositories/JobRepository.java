package br.wagnermorais.gestao__vagas.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.wagnermorais.gestao__vagas.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository< JobEntity  ,UUID >{
    // "contains - LIKE"
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);

    List<JobEntity> findByCompanyId(UUID companyId);

}
