package com.example.personal_api.dto;

public record AlunoDetalheResponse(
        Long id,
        String nome,
        Integer idade,
        Double pesoAtual,
        Double altura,
        String objetivo,
        TreinoAtivoResponse treinoAtivo
) {}
