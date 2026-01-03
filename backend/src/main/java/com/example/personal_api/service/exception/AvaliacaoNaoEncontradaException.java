package com.example.personal_api.service.exception;


public class AvaliacaoNaoEncontradaException extends RuntimeException {

    public AvaliacaoNaoEncontradaException(Long id) {
        super("Avaliação com id " + id + " não encontrada");
    }
}
