package com.example.personal_api.dto;

public record ExercicioResponse(
        Long id,
        String nome,
        Integer series,
        Integer repeticoes,
        String observacoes
) {}

