package br.com.wagnermorais.fron_gestao_vagas.modules.candidate.controller.service;


import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.wagnermorais.fron_gestao_vagas.modules.candidate.controller.dto.Token;

@Service
public class CandidateService {
    
    public Token login(String username , String password){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String  ,String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);

        HttpEntity<Map<String,String>> request = new HttpEntity<>(data, headers);

        var result = restTemplate.postForObject("http://localhost:8080/candidate/auth", request, Token.class);
        System.out.println("Retorno da requisição");
        System.out.println(result.getAcess_token());
        return result;
        
    }
}
