package br.com.wagnermorais.fron_gestao_vagas.modules.candidate.controller.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ApplyJobService {

    public String execute(String token, UUID idJob){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        

        HttpEntity<UUID> request = new HttpEntity<>(idJob, headers);

        var result = restTemplate.postForObject("http://localhost:8080/candidate/job/apply", request, String.class);
        System.out.println("Retorno da requisição");
        System.out.println(result);
        return result;
    }
    
}
