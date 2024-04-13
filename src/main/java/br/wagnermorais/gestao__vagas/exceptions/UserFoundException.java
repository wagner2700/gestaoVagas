package br.wagnermorais.gestao__vagas.exceptions;

public class UserFoundException extends RuntimeException{
    
    public UserFoundException(){
        super("Usuario já existe.");
    }
}
