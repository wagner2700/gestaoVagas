package br.wagnermorais.gestao__vagas.modules.company.UseCases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.wagnermorais.gestao__vagas.modules.company.entities.JobEntity;
import br.wagnermorais.gestao__vagas.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsCompanyUseCase {
    

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(UUID companyId){
        return this.jobRepository.findByCompanyId(companyId);

    }
}
