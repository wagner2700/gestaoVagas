package br.wagnermorais.gestao__vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.wagnermorais.gestao__vagas.exceptions.JobNotFoundException;
import br.wagnermorais.gestao__vagas.exceptions.UserNotFoundException;
import br.wagnermorais.gestao__vagas.modules.candidate.CandidateRepository;
import br.wagnermorais.gestao__vagas.modules.candidate.entity.ApplyJobEntity;
import br.wagnermorais.gestao__vagas.modules.candidate.repository.ApplyJobRepository;
import br.wagnermorais.gestao__vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private  JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate , UUID idJob){

        // validar se usuario existe
        this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        // validar se a vaga existe
        this.jobRepository.findById(idJob)
        .orElseThrow(()->{
            throw new JobNotFoundException();
        });

        // Se inscrever na vaga
        var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob).build();

        applyJob =  applyJobRepository.save(applyJob);

        return applyJob;

    }
}
