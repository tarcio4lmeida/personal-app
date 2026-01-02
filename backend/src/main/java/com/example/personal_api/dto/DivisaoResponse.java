package com.example.personal_api.dto;

import java.util.List;

public record DivisaoResponse(
        Long id,
        String nome,
        String grupoMuscular,
        List<ExercicioResponse> exercicios
) {}
