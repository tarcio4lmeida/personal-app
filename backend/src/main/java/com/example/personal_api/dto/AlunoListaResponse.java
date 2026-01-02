package com.example.personal_api.dto;

import java.time.LocalDate;

public record AlunoListaResponse(
        Long id,
        String nome,
        String objetivo,
        Double pesoAtual,
        LocalDate dataUltimaAvaliacao
) {}
