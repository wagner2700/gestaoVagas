package br.wagnermorais.gestao__vagas.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("User not found");
    }
}
