package com.example.personal_api.dto;

import java.util.List;

public record TreinoAtivoResponse(
        Long id,
        String nome,
        String observacoes,
        List<DivisaoResponse> divisoes
) {}