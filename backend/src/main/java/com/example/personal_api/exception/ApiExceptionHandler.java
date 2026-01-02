package com.example.personal_api.exception;

import com.example.personal_api.service.exception.AlunoNaoEncontradoException;
import com.example.personal_api.service.exception.TreinoAtivoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<ApiError> handleAlunoNaoEncontrado(
            AlunoNaoEncontradoException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(404, ex.getMessage()));
    }

    @ExceptionHandler(TreinoAtivoNaoEncontradoException.class)
    public ResponseEntity<ApiError> handleTreinoNaoEncontrado(
            TreinoAtivoNaoEncontradoException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(404, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(500, "Erro interno inesperado"));
    }
}