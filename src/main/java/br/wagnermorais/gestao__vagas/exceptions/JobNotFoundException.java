package br.wagnermorais.gestao__vagas.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(){
        super("Job not found");
    }
}
