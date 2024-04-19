package br.wagnermorais.gestao__vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTCandidateProvider {
    
    @Value("${security.token.secret.candidate}")
    private String secreKey;

    public DecodedJWT validateToken(String token){
        token = token.replace("Bearer ", "");
        System.out.println("JWTCANDIDATEPROVIDER " + token);
        Algorithm algorithm = Algorithm.HMAC256(secreKey);

        try {
            var tokenDecoded = JWT.require(algorithm)
            .build().verify(token);
            System.out.println("JWTCANDIDATEPROVIDER2 " + tokenDecoded);
            return tokenDecoded;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            System.out.println("JWTCANDIDATEPROVIDER2 dEUERRADO");
            return null;
        }
    }
}