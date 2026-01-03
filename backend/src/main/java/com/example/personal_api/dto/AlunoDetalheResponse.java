package com.example.personal_api.dto;

import java.time.LocalDate;

public record AlunoDetalheResponse(
        Long id,
        String nome,
        Integer idade,
        Double pesoAtual,
        Double altura,
        String objetivo,
        LocalDate dataUltimaAvaliacao,
        Long diasDesdeUltimaAvaliacao,
        TreinoAtivoResponse treinoAtivo
) {}
