package br.wagnermorais.gestao__vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.wagnermorais.gestao__vagas.modules.company.entities.CompanyEntity;



public interface CompanyRepository extends JpaRepository<CompanyEntity , UUID>{
    // procurar e se não  entrar retornar algumas opções
    Optional<CompanyEntity> findByUsernameOrEmail(String username , String email);
    Optional<CompanyEntity> findByUsername(String username);
}
