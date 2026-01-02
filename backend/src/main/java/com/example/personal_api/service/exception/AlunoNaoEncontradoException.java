package com.example.personal_api.service.exception;

public class AlunoNaoEncontradoException extends RuntimeException {
    public AlunoNaoEncontradoException(Long id) {
        super("Aluno com id " + id + " não encontrado");
    }
}
