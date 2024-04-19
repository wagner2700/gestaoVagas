package br.wagnermorais.gestao__vagas.modules.company.UseCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.wagnermorais.gestao__vagas.exceptions.UserFoundException;
import br.wagnermorais.gestao__vagas.modules.company.entities.CompanyEntity;
import br.wagnermorais.gestao__vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public CompanyEntity execute( CompanyEntity companyEntity){

        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((user) ->{
            throw new  UserFoundException();
        });
        // Criptografar senha
        var password = passwordEncoder.encode(companyEntity.getPassword());

        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
