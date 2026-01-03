package com.example.personal_api.dto;

import java.time.LocalDate;

public record AvaliacaoFisicaListaResponse(
        Long id,
        LocalDate dataAvaliacao,
        String nome
) {}