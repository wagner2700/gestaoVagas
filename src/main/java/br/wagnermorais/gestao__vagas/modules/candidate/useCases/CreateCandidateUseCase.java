package br.wagnermorais.gestao__vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.wagnermorais.gestao__vagas.exceptions.UserFoundException;
import br.wagnermorais.gestao__vagas.modules.candidate.CandidateEntity;
import br.wagnermorais.gestao__vagas.modules.candidate.CandidateRepository;

// Gerencia ciclo
@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   
    
    public CandidateEntity execute(CandidateEntity candidateEntity){
        // Verificar se já esta cadastrado
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });

        var password = passwordEncoder.encode(candidateEntity.getPassword());

        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity); 
    }
}
