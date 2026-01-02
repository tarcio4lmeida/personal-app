package com.example.personal_api.service.exception;

public class TreinoAtivoNaoEncontradoException extends RuntimeException {
    public TreinoAtivoNaoEncontradoException(Long alunoId) {
        super("Aluno " + alunoId + " não possui treino ativo");
    }
}